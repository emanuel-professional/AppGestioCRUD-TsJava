/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.controllers;
import cat.proven.gestionapp.model.Film;
import cat.proven.gestionapp.model.persist.FilmArrayDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alumne
 */
public class FilmServlet extends HttpServlet {

   private FilmArrayDao filmDao;
    @Override
    public void init(){
        filmDao = FilmArrayDao.getInstance();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        String title = request.getParameter("name");
        String director = request.getParameter("director");
        String description = request.getParameter("description");
        String oldtitle = request.getParameter("oldtitle");
        
        if (action != null) {
            switch (action) {
                case "list_all_films": 
                    listAllFilmsHtml(request,response);
                    break;
                case "list_friend_by_title": 
                    listFilmByName(request,response,title);
                    break;
                case "add_new_film": //add
                    addNewFilm(request,response,title,director,description);
                    break; 
                case "modify_film": //modify
                    modifyFilm(request,response,oldtitle,title,director,description);
                    break;
                case "delete_film": //delete
                    deleteFilm(request,response,title);
                    break;
                case "listAllFilmsJson":
                    listAllFilmsJson(request, response);
                    break;
                case "list_fiendbytitleJson":
                   list_fiendbyTitleJson(request,response,title);
                default: //unknown option.
                  
                    break;
            }               
        } else { // parameter action needed

        }
        
    }
    
    public void addNewFilm(HttpServletRequest request, HttpServletResponse respons, String title, String director,String description) throws ServletException, IOException{
        if(title != null || director != null){
            filmDao.insertFilm(new Film(title,director,description));
            request.setAttribute("mgs", "Successfully added");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/message.jsp");
            rd.forward(request, respons);
        }
    }
    
    public void modifyFilm(HttpServletRequest request, HttpServletResponse respons,String oldtitle, String title, String director,String description) throws ServletException, IOException{
        if(title != null || director != null){
            filmDao.updateFilm(new Film(oldtitle), new Film(title,director,description));
            request.setAttribute("mgs", "Successfully edited");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/message.jsp");
            rd.forward(request, respons);
        }
    }
    
    public void deleteFilm(HttpServletRequest request, HttpServletResponse respons,String title) throws ServletException, IOException{
        if(title != null){
            filmDao.deleteFilm(new Film(title));
            request.setAttribute("mgs", "Successfully deleted");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/message.jsp");
            rd.forward(request, respons);
        }
            
    }
        
    public void listAllFilmsHtml(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        List<Film> entityList =  (List<Film>) filmDao.listFilms();
        request.setAttribute("films", entityList);
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/listAll.jsp");
	rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listFilmByName(HttpServletRequest request, HttpServletResponse response, String name) {
        Film film =  filmDao.finByNameFilm(name);
        if(film != null){
           request.setAttribute("film",film);
           RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/listFilm.jsp");
            try { 
                rd.forward(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(FilmServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FilmServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            request.setAttribute("mgs", "Error film not found!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/message.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {  }
        }
  
    }

    private void listAllFilmsJson(HttpServletRequest request, HttpServletResponse response)  {
        List<Film> entityList = (List<Film>) filmDao.listFilms();
        try (PrintWriter out = response.getWriter()) {
            out.print(new Gson().toJson(entityList));
        } catch (IOException ex) {
            System.out.println(ex.getCause());
       }
    }
    
    private void list_fiendbyTitleJson(HttpServletRequest request, HttpServletResponse response,String name){
        Film film =  filmDao.finByNameFilm(name);
        if(film != null){
           try (PrintWriter out = response.getWriter()) {
                out.print(new Gson().toJson(film));
            } catch (IOException ex) {
                System.out.println(ex.getCause());
            } 
        }else{
            request.setAttribute("mgs", "Error film not found!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/message.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {  }
        }
            
            
        
    }

}
