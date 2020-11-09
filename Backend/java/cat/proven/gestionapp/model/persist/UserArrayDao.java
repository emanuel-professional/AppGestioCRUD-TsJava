/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.model.persist;

import java.util.ArrayList;
import java.util.List;
import proven.friends.model.User;

/**
 *
 * @author alumne
 */
public class UserArrayDao implements UserDaoInterface {
    
    private final List<User> data;
    
    public UserArrayDao()  {
        data = new ArrayList<>(); 
        loadTestData();
    }   

    @Override
    public boolean login(User entity) {
       boolean exists = false;
        for (User object: data) {
            if(object.getUserId().equals(entity.getUserId()) && object.getPassword().equals(entity.getPassword())) {
                exists = true;
            }                
        }        
        return exists;
    }
    
    @Override
    public User find(String userId) {
        User u  = null;
        boolean exists  = false;
        for(int i=0; i < data.size() && !exists; i++) { 
            User object = data.get(i);
            if(object.getUserId().equals(userId)){
                u = object;
                exists  = true;
            }
        }
        return u;
    }
    

    @Override
    public List<User> findAll() {
        return (List<User>) data;
    }

    @Override
    public int insert(User entity) {
        int rowsAffected;
        boolean alreadyExists = data.contains(entity);
        if (alreadyExists) {
            rowsAffected = 0;
        }
        else {
            boolean success = data.add(entity);
            if (success) rowsAffected = 1;
            else rowsAffected = 0;
        }
        return rowsAffected;    
    }

    @Override
    public int remove(User entity) {
        int rowsAffected;
        if (data.contains(entity)) {
            data.remove(entity);
            rowsAffected = 1;
        }
        else {
            rowsAffected = 0;
        }
        return rowsAffected;    
    }

    @Override
    public int update(User oldEntity, User newEntity) {
        int rowsAffected;
        int index = data.indexOf(oldEntity);
        if (index >= 0) {
            data.set(index, newEntity);
            rowsAffected = 1;
        }
        else {
            rowsAffected = 0;
        }
        return rowsAffected;   
    }
    
    private void loadTestData() {
        data.add(new User("peter", "12345","user"));
        data.add(new User("admin", "12345","admin"));
        data.add(new User("jonh", "12345","user"));
    }

   
    
}
