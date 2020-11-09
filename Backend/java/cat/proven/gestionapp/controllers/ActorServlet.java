/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.controllers;

import cat.proven.gestionapp.model.Actor;
import cat.proven.gestionapp.model.persist.ActorArrayDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ActorServlet extends HttpServlet {
    
    ActorArrayDao actorDao;
    @Override
    public void init(){
        actorDao = ActorArrayDao.getInstance();
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
        String name = request.getParameter("name");
        String yearbirth = request.getParameter("yearbirth");
        String oldname = request.getParameter("oldname");
        if (action != null) {
            switch (action) {
                case "list_all_actors": 
                    listAllActors(request,response);
                    break;
                case "list_actor_by_name": 
                    listActorByName(request,response,name);
                    break;
                case "add_new_actor": 
                    addNewActor(request,response,name,yearbirth);
                    break; 
                case "modify_actor": 
                    modifyActor(request,response,oldname,name,yearbirth);
                    break;
                case "delete_actor": //
                    deleteActor(request,response,name);
                    break;
                case "list_allactorsJson":
                    list_allactorsJson(request,response);
                    break;
                case "list_allactorsbyName":
                    list_allactorsbyName(request,response,name);
                default: //unknown option.
                  
                    break;
            }               
        } else { // parameter action needed

        }
    }
    
    public void addNewActor(HttpServletRequest request, HttpServletResponse respons, String nom, String birth) throws ServletException, IOException{
        if(nom != null || birth != null){
            actorDao.insertActor(new Actor(nom,Integer.parseInt(birth)));
            request.setAttribute("mgs", "Successfully added");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspActor/message.jsp");
            rd.forward(request, respons);
        }
    }
    
    public void modifyActor(HttpServletRequest request, HttpServletResponse respons,String oldname, String name, String yearBirth) throws ServletException, IOException{
        if(oldname != null || yearBirth != null){
            actorDao.updateActor(new Actor(oldname), new Actor(name,Integer.parseInt(yearBirth)));
            request.setAttribute("mgs", "Successfully edited");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspActor/message.jsp");
            rd.forward(request, respons);
        }
    }
    
    public void deleteActor(HttpServletRequest request, HttpServletResponse respons,String name) throws ServletException, IOException{
        if(name != null){
            actorDao.deleteActor(new Actor(name));
            request.setAttribute("mgs", "Successfully deleted");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspActor/message.jsp");
            rd.forward(request, respons);
        }
            
    }
    
    public void listAllActors(HttpServletRequest request, HttpServletResponse respons) throws ServletException, IOException{
        List<Actor> entityList =  (List<Actor>) actorDao.listActors();
        request.setAttribute("actors", entityList);
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspActor/listAll.jsp");
	rd.forward(request, respons);
    }
    
    private void listActorByName(HttpServletRequest request, HttpServletResponse response, String name) {
        Actor actor = actorDao.finByNameActor(name);
        if(actor != null){
           request.setAttribute("actor",actor);
           RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspActor/listActor.jsp");
            try { 
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(FilmServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            request.setAttribute("mgs", "Error actor not found!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/message.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {  }
        }
  
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

    private void list_allactorsJson(HttpServletRequest request, HttpServletResponse response) {
       List<Actor> entityList =  (List<Actor>) actorDao.listActors();
        try (PrintWriter out = response.getWriter()) {
            out.print(new Gson().toJson(entityList));
        } catch (IOException ex) {
            System.out.println(ex.getCause());
       } 
    }

    private void list_allactorsbyName(HttpServletRequest request, HttpServletResponse response, String name) {
        Actor actor = actorDao.finByNameActor(name);
        if(actor != null){
           try (PrintWriter out = response.getWriter()) {
                out.print(new Gson().toJson(actor));
           } catch (IOException ex) {
            System.out.println(ex.getCause());
           }     
        }else{
            request.setAttribute("mgs", "Error actor not found!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jspFilm/message.jsp");
            try {
                rd.forward(request, response);
            } catch (ServletException | IOException ex) {  }
        }
    }

}
