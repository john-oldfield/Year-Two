/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author 2oldfj45
 */
public class CreateAgentController implements Serializable{
    
     @FXML public void GoToMainMenu(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        } 
     
     //Code for Create Agent
    @FXML
    TextField username, name, address, phone;
    
    @FXML
    PasswordField password;
    
    @FXML
    Button submit;
    
    @FXML
    Label confirmation;
    
    @FXML public void createAgent() throws FileNotFoundException, ClassNotFoundException, IOException
    {
        String usrname = username.getText();
        String pssword = password.getText();
        String nme = name.getText();
        String adress = address.getText();
        String phne = phone.getText();
        
        Validation validation = new Validation();
        validation.validateName(nme);
        validation.validatePhone(phne);
        
        JJT jjt = new JJT();
        ArrayList<Agent> agentList = jjt.readListOfAgents();
        int i = agentList.size() + 1;
        
        if (validation.valid == true)
        {
            
        agentList.add(new Agent(i++,usrname, pssword, nme, adress, phne, 0, false, 0, 0, 0));
        confirmation.setText("Agent created Successfully.");
            
        }
        else{
            confirmation.setText("An Error Occured.");
        }
        
        
        jjt.saveListOfAgents(agentList);
    
    }


}
