/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.gestionapp.services;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
 
/**
 * L'anotació @ApplicationPath determina la URL Base de tots els controladors.
 * @author Alumne
 */
@ApplicationPath("services")
public class ServiceConfiguration extends Application {
 
}