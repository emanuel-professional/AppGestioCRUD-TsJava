/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.model.persist;

import cat.proven.gestionapp.model.Actor;
import java.util.List;

/**
 * @author Emanuel Canizales
 */
public interface ActorDaoInterface {
    /**
     * This method insert an actor
     * @param actor
     * @return true if it was successfully otherwise return false
     */
     public boolean insertActor(Actor actor);
     /**
      * This method delete an actor
      * @param actor
      * @return true if it was successfully otherwise return false
      */
     public boolean deleteActor(Actor actor);
     /**
      * This method return all actors
      * @return actors
      */
     public List<Actor> listActors();
     /**
      * This method update an actor
      * @param oldActor
      * @param newActor
      * @return true if it was successfully otherwise return false
      */
     public boolean updateActor(Actor oldActor,Actor newActor);
     /**
      * This method find an actor
      * @param actor
      * @return true if it was successfully otherwise return false
      */
     public boolean findActor(Actor actor);
     
     public Actor finByNameActor(String name);
}
