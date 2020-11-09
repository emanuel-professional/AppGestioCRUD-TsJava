/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.filters;
import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
 
	private ServletContext context;
 
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}
 
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                this.context.log("FILTRE AuthenticationFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
 
		String uri = req.getRequestURI();
		this.context.log("Requested Resource::"+uri);

		HttpSession session = req.getSession(false);

		if((session != null && session.getAttribute("user") != null) || (uri.endsWith("html") || uri.endsWith("LoginServlet")) ){
                    chain.doFilter(request, response);
		}else{
                    this.context.log("Unauthorized access request");
                    res.sendRedirect("login.html");
		}
	}

    @Override
	public void destroy() {
		//close any resources here
	}
 
}
