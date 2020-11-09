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
public class Actor {
    
    private String name;
    private int yearBirth;
    
    public Actor(String name, int yearBirth){
        this.name = name;
        this.yearBirth = yearBirth;
    }
    public Actor(String name){
        this.name = name;
    }
    public Actor(){}

    public String getName() {
        return name;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.name);
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
        final Actor other = (Actor) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Actor[" + "name=" + name + ", yearBirth=" + yearBirth + ']';
    }
    
    
    
    
    
}
