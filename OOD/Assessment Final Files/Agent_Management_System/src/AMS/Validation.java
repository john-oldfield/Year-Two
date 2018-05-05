/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMS;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author joldf_000
 */
public class Validation 
{
    public boolean valid = false;

    public void validateName(String name)
    {
        if(name.matches("[a-zA-z]"))
        {
            System.out.println("Name is Valid.");
            valid = true;
            
        }
        else{
            System.out.println("Name is not valid.");
//            Alert alert = new Alert(AlertType.ERROR);
//            alert.setTitle("Invalid Name");
//            alert.setContentText("Name can only contain letters!");
//
//            alert.showAndWait();
//            valid = false;
        }
        
    }
    
    public void validatePhone(String phone)
    {
        if(phone.length() == 11 && phone.matches("[0-9]+"))
        {
            System.out.println("Phone number is valid.");
            valid = true;
        }
        else
        {
            System.out.println("Phone number is not valid.");
            valid = false;
        }
            
    }
    
    
}
