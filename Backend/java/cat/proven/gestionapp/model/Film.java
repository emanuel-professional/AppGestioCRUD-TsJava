/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.model;

import java.util.Objects;

/**
 *
 * @author Emanuel Canizales
 */
public class Film {
    
    private String title;
    private String director;
    private String descripcion;
    private int year;

    public Film(String title, String director, String descripcion, int year) {
        this.title = title;
        this.director = director;
        this.descripcion = descripcion;
        this.year = year;
    }
    
    public Film(String title){
        this.title = title;
    }
    
    public Film(String title, String director,String description){
        this.title = title;
        this.director = director;
        this.descripcion = description;
    }
    
    public Film(){}
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.title);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Film other = (Film) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString() {
        return "Film{" + "title=" + title + ", director=" + director + ", descripcion=" + descripcion + ", year=" + year + '}';
    }
    
    
    
}
