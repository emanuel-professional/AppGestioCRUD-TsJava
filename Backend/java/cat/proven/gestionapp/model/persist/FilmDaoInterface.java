/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.model.persist;
import cat.proven.gestionapp.model.Film;
import java.util.List;

/**
 *
 * @author Emanuel Canizales
 */
public interface FilmDaoInterface {
    /**
     * This method insert a film
     * @param film
     * @return true if it was successfully otherwise return false
     */
    public boolean insertFilm(Film film);
    /**
     * This method delete Film 
     * @param film
     * @return true if it was successfully otherwise return false
     */
    public boolean deleteFilm(Film film);
    /**
     * This method list all Films
     * @return true if it was successfully otherwise return false
     */
    public List<Film> listFilms();
    /**
     * This method update film
     * @param oldFilm 
     * @param newFilm
     * @return true if it was successfully otherwise return false
     */
    public boolean updateFilm(Film oldFilm,Film newFilm);
    /**
     * This method find a film
     * @param film
     * @return true if it was successfully otherwise return false
     */
    public boolean findFilm(Film film);
    
    public Film finByNameFilm(String filmName);
}
