/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.services;

import cat.proven.gestionapp.model.Film;
import cat.proven.gestionapp.model.persist.FilmArrayDao;
import cat.proven.gestionapp.model.persist.FilmDaoInterface;
import com.google.gson.Gson;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author alumne
 */
@Path("film")
public class FilmService {
    
    FilmDaoInterface filmdao;
 
    public FilmService(@Context ServletContext context){        
        if(context.getAttribute("filmdao") != null)
            filmdao = (FilmArrayDao) context.getAttribute("filmdao");
        else{
            filmdao = FilmArrayDao.getInstance();
            context.setAttribute("filmdao", filmdao);
        }
   }
   
    /**
     * http://localhost:8080/AppGestor/services/film/find_all_films
     * @return 
     */
   @GET
   @Path("/find_all_films")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String findAllFilms(){
      return new Gson().toJson(filmdao.listFilms());
   }
   
   /**
    * http://localhost:8080/AppGestor/services/film/findbytitle/Love
    * @param name
    * @return 
    */
   @Path("/findbytitle/{title}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String findFilmbytitle(@PathParam("title") String  name){
        return new Gson().toJson(filmdao.finByNameFilm(name));
   }
   
   /**
    * http://localhost:8080/AppGestor/services/film/insert_film_query?title=idsj&director=klwm&description=asdlkdas&year=54
    * @param title
    * @param director
    * @param description
    * @param year
    * @return 
    */
   @GET
   @Path("/insert_film_query")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String insertFilm(@QueryParam("title") String title,
                            @QueryParam("director") String director,
                            @QueryParam("description")String description,
                               @QueryParam("year") int year){
       
       if(filmdao.insertFilm(new Film(title,director,description,year))){
           return new Gson().toJson("Successfully insert");
       }else{
           return new Gson().toJson("Error insert");
       }
   }
   
   /**
    * http://localhost:8080/AppGestor/services/film/deletefilm/Love
    * @param title
    * @return 
    */
   @Path("/deletefilm/{title}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String deleteFilm(@PathParam("title") String  title){
        
       if(filmdao.deleteFilm(new Film(title))){
           return new Gson().toJson("Successfully delete");
       }else{
          return new Gson().toJson("Error delete"); 
       } 
   }
   
   /**
    * http://localhost:8080/AppGestor/services/film/update_film_query?title=daskj&director=dsalqkls&description=skiqkal&year=95&oldtitle=Fruite
    * @param title
    * @param director
    * @param description
    * @param year
    * @param oldtitle
    * @return 
    */
   @GET
   @Path("/update_film_query")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateFilm(@QueryParam("title") String title,
                            @QueryParam("director") String director,
                            @QueryParam("description")String description,
                               @QueryParam("year") int year,
                               @QueryParam("oldtitle") String oldtitle){
       
       if(filmdao.updateFilm(new Film(oldtitle), new Film(title,director,description,year))){
           return new Gson().toJson("Successfully update");
       }else{
           return new Gson().toJson("Error update");
       }
   }
   
   
   
   
}
