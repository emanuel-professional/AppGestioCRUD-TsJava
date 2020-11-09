/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.model;

import cat.proven.gestionapp.model.persist.ActorArrayDao;
import cat.proven.gestionapp.model.persist.FilmArrayDao;
import java.util.List;

/**
 *
 * @author Emanuel Canizales
 */
public class Model{
    
    private final ActorArrayDao actorArrayDao;
    private final FilmArrayDao filmArrayDao;
    public Model(){
        filmArrayDao = FilmArrayDao.getInstance();
        actorArrayDao = ActorArrayDao.getInstance();
    }
    
    public boolean insertFilm(Film film){
        return filmArrayDao.insertFilm(film);
    }
    
    public boolean deleteFilm(Film film){
        return filmArrayDao.deleteFilm(film);
    }
    
    public List<Film> listFilms(){
        return filmArrayDao.listFilms();
        
    }
    
    public boolean updateFilm(Film oldFilm, Film newFilm){
        return filmArrayDao.updateFilm(oldFilm, newFilm);
    }
    
    public boolean findFilm(Film film){
        return filmArrayDao.findFilm(film);
    }
    
    public Film finByNameFilm(String filmName){
        return filmArrayDao.finByNameFilm(filmName);
    }
    
    public boolean insertActor(Actor actor){  
       return actorArrayDao.insertActor(actor);
    }
    
    public boolean deleteActor(Actor actor){
        return actorArrayDao.deleteActor(actor);
    }
    
    public List<Actor> listActors(){
        return actorArrayDao.listActors();
    }
    
    public boolean updateActor(Actor oldActor, Actor newActor){
        return actorArrayDao.updateActor(oldActor, newActor);
    }
    
    public boolean findActor(Actor actor){
        return actorArrayDao.findActor(actor);
    }
   
}
