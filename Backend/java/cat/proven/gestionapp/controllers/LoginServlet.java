/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.controllers;

import cat.proven.gestionapp.model.persist.UserArrayDao;
import cat.proven.gestionapp.model.persist.UserDaoInterface;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import proven.friends.model.User;
 
public class LoginServlet extends HttpServlet {
 
    private UserDaoInterface users;
    private ServletContext context;
 
    @Override
    public void init() throws ServletException {
        users = new UserArrayDao();
        context = this.getServletContext();
    }
 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        String user = req.getParameter("user");
        String password = req.getParameter("password");
        this.context.log(users + ":" + user + "/" + password);
        if (user != null && password != null) {
            if (users.login(new User(user, password))) {
                this.context.log("generate Session");
                HttpSession session = req.getSession();
                User u = users.find(user);
                session.setAttribute("user", user);
                session.setAttribute("role", u.getRole());
                response(req, resp, "Login ok ");
            }
        }
        RequestDispatcher rd = 
                getServletContext().getRequestDispatcher("/login.html");
	rd.forward(req, resp);
    }
 
    private void response(HttpServletRequest req, HttpServletResponse resp, String msg)
            throws IOException, ServletException {
        req.setAttribute("message", msg);
        RequestDispatcher rd = 
                getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/message.jsp");
	rd.forward(req, resp);
    }
}