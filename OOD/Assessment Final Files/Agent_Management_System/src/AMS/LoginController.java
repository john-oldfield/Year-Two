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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author joldf_000
 */
public class LoginController implements Initializable 
{
    @FXML TextField username;
    @FXML PasswordField password;
    @FXML Label status;
    @FXML Button submit;
    
    JJT jjt;
    ArrayList<Agent> agentList;
    public LoginController() throws IOException{
        try {
            jjt = new JJT();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        agentList= jjt.getAgentList();
    }
    
    public void Login(ActionEvent event) throws Exception
        {
            for(Agent a:agentList)
            {
                System.out.println(a.getId());
                
                if(username.getText().equals(a.getUsername())&& password.getText().equals(a.getPassword()))
                {
                    a.setActive(true);
                    jjt.updateAgent(a);
                    ((Node)event.getSource()).getScene().getWindow().hide(); 
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));

                    Scene scene = new Scene(root);

                    stage.setTitle("JJT Agent Management System");
                    stage.setScene(scene);
                    stage.show();
                    
                    
                    
                }
                else{
                    status.setVisible(true);
                }
            }

   
            }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        /* Set Text Code For Quicker Access/Testing ONLY */
        username.setText("JOldfield1");
        password.setText("pass");
    }

           
}

        
  
