/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author 2oldfj45
 */
public class ModifyAgentController implements Initializable 
{
    
    @FXML
    ChoiceBox<String> agents;
    
    @FXML
    TextField agentUsername, agentName, agentAddress, agentPhone, id;
    
    @FXML
    PasswordField agentPassword;
      
    @FXML
    Button submit;
    
    @FXML 
    public void GoToMainMenu(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateChoiceBox();
            choiceBoxChangeValueAction();
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(ModifyAgentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void populateChoiceBox() throws FileNotFoundException, ClassNotFoundException, IOException
    {
        JJT jjt = new JJT();
        ArrayList<Agent> agentList = jjt.readListOfAgents();
        for(Agent a:agentList)
        {
            agents.getItems().add(a.getUsername());
        }
    }
    public void choiceBoxChangeValueAction()
    {
        agents.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> 
        {            
            JJT jjt;
            try {
                jjt = new JJT();
            Agent agent = jjt.returnAgentBasedOnUsername(newValue);
            id.setText(Integer.toString(agent.getId()));
            agentUsername.setText(agent.getUsername());
            agentPassword.setText(agent.getPassword());
            agentName.setText(agent.getName());
            agentAddress.setText(agent.getAddress());
            agentPhone.setText(agent.getPhone());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ModifyAgentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ModifyAgentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ModifyAgentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    
    @FXML public void saveChanges() throws ClassNotFoundException, IOException {
       
            String username = agentUsername.getText();
            String password = agentPassword.getText();
            String name = agentName.getText();
            String address = agentAddress.getText();
            String phone = agentPhone.getText();
            JJT jjt = null;
        try {
            jjt = new JJT();
        } catch (IOException ex) {
            Logger.getLogger(ModifyAgentController.class.getName()).log(Level.SEVERE, null, ex);
        }
            ArrayList<Agent> agentList = jjt.readListOfAgents();
            
            for(Agent a:agentList){
                if(a.getId() == Integer.parseInt(id.getText())){
                    System.out.println(a.getUsername());
                    if(!a.getUsername().equals(username)){
                        a.setUsername(username);
                        
                    }
                    if(!a.getPassword().equals(password)){
                        a.setPassword(password);
                        
                    }
                    if(!a.getName().equals(name)){
                        a.setName(name);
                        
                    }
                    if(!a.getAddress().equals(address)){
                        a.setAddress(address);
                        
                    }
                    if(!a.getPhone().equals(phone)){
                        a.setPhone(phone);
                        
                    }
                    
                }
            }
            jjt.saveListOfAgents(agentList);
            
            
            
            
           
        }
                        
}
  
                    
                    
 
        
        
    
    
     
    

