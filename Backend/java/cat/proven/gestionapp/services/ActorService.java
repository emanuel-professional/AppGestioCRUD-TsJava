/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.services;
import cat.proven.gestionapp.model.Actor;
import cat.proven.gestionapp.model.persist.ActorArrayDao;
import cat.proven.gestionapp.model.persist.ActorDaoInterface;
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
 * http://localhost:8080/AppGestor/services/actor
 * @author Alumne
 */
@Path("actor")
public class ActorService {
     ActorDaoInterface actordao;
 
    public ActorService(@Context ServletContext context){        
        if(context.getAttribute("actordao") != null)
            actordao= (ActorArrayDao) context.getAttribute("actordao");
        else{
            actordao = ActorArrayDao.getInstance();
            context.setAttribute("actordao", actordao);
        }
   }
    
   /**
    * List all actors
    * http://localhost:8080/AppGestor/services/actor/find_all_actors
    * @return 
    */
   @GET
   @Path("/find_all_actors")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String findAllUsers(){
      return new Gson().toJson(actordao.listActors());
   }
   
   /**
    * This method search an actor by name and return it
    * http://localhost:8080/AppGestor/services/actor/findbyname
    * @param name
    * @return 
    */
   @Path("/findbyname/{name}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String findActorbyname(@PathParam("name") String  name){
        return new Gson().toJson(actordao.finByNameActor(name));
   }
   
   /**
    * This method add a new actor given by parameter
    * http://localhost:8080/AppGestor/services/actor/insert_actor_query?name=dssda&yearBirth=98
    * @param name
    * @param yearBirth
    * @return 
    */
   @GET
   @Path("/insert_actor_query")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String insertActor(@QueryParam("name") String name,
                               @QueryParam("yearBirth") int yearBirth){
       
        Actor actor = new Actor(name,yearBirth);
        if(actordao.insertActor(actor)){
            return new Gson().toJson("Successfully insert");
        }else{
            return new Gson().toJson("Error Insert");
        }
   }
   
   /**
    * This method delete an actor and inform the user 
    * if the procediment was successfully or not
    * http://localhost:8080/AppGestor/services/actor/deleteactor/Emanuel
    * @param name
    * @return 
    */
   @Path("/deleteactor/{name}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String deleteActor(@PathParam("name") String  name){
        
       if(actordao.deleteActor(new Actor(name))){
           return new Gson().toJson("Successfully delete");
       }else{
          return new Gson().toJson("Error delete"); 
       } 
   }
   
   /**
    * This method update an actor given by parameter, of course 
    * indicating the old name
    * http://localhost:8080/AppGestor/services/actor/update_actor_query?name=elsk&yearBirth=98&oldname=Emanuel
    * @param name
    * @param yearBirth
    * @param oldname
    * @return a message
    */
   @GET
   @Path("/update_actor_query")
   @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateActor(@QueryParam("name") String name,
                               @QueryParam("yearBirth") int yearBirth,
                               @QueryParam("oldname") String oldname){
       
        Actor actor = new Actor(name,yearBirth);
        if(actordao.updateActor(new Actor(oldname),new Actor(name,yearBirth))){
            return new Gson().toJson("Successfully update");
        }else{
            return new Gson().toJson("Error update");
        }
   }
   
   
   
   
 
 
 
}