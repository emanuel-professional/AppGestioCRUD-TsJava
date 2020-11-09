/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.model.persist;

import cat.proven.gestionapp.model.Actor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emanuel Canizales
 */
public class ActorArrayDao implements ActorDaoInterface{
    
    private final List<Actor> actors;
    private static ActorArrayDao instance;
    
    /**
     * This method allow get only 1 instance of Actors
     * @return new object of ActorArrayDao
     */
    public static ActorArrayDao getInstance(){
      if (instance == null) {
           instance = new ActorArrayDao();
      }
      return instance;
    }
    
    /**
     * This Constructor is responsible to for loading the database
     */
    private ActorArrayDao(){
        actors = new ArrayList<>();
        actors.add(new Actor("Emanuel",97));
        actors.add(new Actor("Jorge",96));
        actors.add(new Actor("Pedro",86));
    }
    
    @Override
    public boolean insertActor(Actor actor){
       return actors.add(actor);
    }
    
    @Override
    public boolean deleteActor(Actor actor){
        return actors.remove(actor);
    }

    @Override
    public List<Actor> listActors(){
        return this.actors;
    }
    
    @Override
    public boolean updateActor(Actor oldActor,Actor newActor){
        boolean actorchanged = false; 
        for (Actor actor : actors) {
            if(actor.equals(oldActor)){
                actor.setName(newActor.getName());
                actor.setYearBirth(newActor.getYearBirth());
                actorchanged = true;
            }
        }
        return actorchanged;
    }

    @Override
    public boolean findActor(Actor actor){
        boolean actorfound = false;
        for(Actor ac : actors){
            if(actor.equals(ac)){
                actorfound = true;
                break;
            }
        }
        return actorfound;
    }
    
    @Override
    public Actor finByNameActor(String acname){
        for(Actor ac : actors){
            if(acname.equals(ac.getName())){
                return ac;
            }
        }
        return null;
    }
            
    
}
