/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.model.persist;

import cat.proven.gestionapp.model.Film;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emanuel Canizales
 */
public class FilmArrayDao implements FilmDaoInterface{
    
   private final List<Film> films;
   private static FilmArrayDao instance;
   
   /**
    * This method allow get only 1 instance of Films
    * @return new object of filmArrayDao
    */
   public static FilmArrayDao getInstance(){
      if (instance == null) {
           instance = new FilmArrayDao();
      }
      return instance;
    }
   
   /**
    * This Constructor is responsible to for loading the database
    */
    private FilmArrayDao(){
        films = new ArrayList<>();
        films.add(new Film("Love","Marc Sans","Action",97));
        films.add(new Film("Fruite","Carles Rodriguez","Comedy",97));
        films.add(new Film("Makein UP","James Burbologs","Thriller",97));
        
    }
    @Override
    public boolean insertFilm(Film film) {
        return films.add(film);
    }

    @Override
    public boolean deleteFilm(Film film) {
        return films.remove(film);
    }

    @Override
    public List<Film> listFilms(){
        return this.films;
    }

    @Override
    public boolean updateFilm(Film oldFilm, Film newFilm) {
       boolean filmchanged = false; 
        for (Film film : films) {
            if(film.equals(oldFilm)){
                film.setTitle(newFilm.getTitle());
                film.setDirector(newFilm.getDirector());
                film.setDescripcion(newFilm.getDescripcion());
                film.setYear(newFilm.getYear());
                filmchanged = true;
            }
        }
        return filmchanged;
    }

    @Override
    public boolean findFilm(Film film) {
        boolean filmfound = false;
        for(Film fl : films){
            if(film.equals(fl)){
                filmfound = true;
                break;
            }
        }
        return filmfound;
    }
    
   @Override
    public Film finByNameFilm(String filmName){
        for(Film fl : films){
            if(filmName.equals(fl.getTitle())){
                return fl;
            }
        }
        return null;
    }
    
}
