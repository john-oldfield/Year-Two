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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/* @author John Oldfield */

public class AgentSalesController implements Initializable {
    
    @FXML
    ChoiceBox username;
    
    @FXML
    Label CFSales, GDSales, B182Sales, sales;
    
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
     
    public void populateChoiceBox() throws FileNotFoundException, 
            ClassNotFoundException, IOException
    {
        JJT jjt = new JJT();
        ArrayList<Agent> agentList = jjt.readListOfAgents();
        
        for(Agent a:agentList)
        {
            username.getItems().add(a.getUsername());
        }
    }
    
    public void choiceBoxChangeValueAction()
    {
        username.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> 
        {            
            JJT jjt;
            
            try 
            {
                jjt = new JJT();
                Agent agent = jjt.returnAgentBasedOnUsername((String) newValue);
                int gsales = agent.getSalesForGD();
                int csales = agent.getSalesForCF();
                int bsales = agent.getSalesForB182();
                int totalSales = (gsales + csales + bsales);
                sales.setText(Integer.toString(totalSales));
                CFSales.setText(Integer.toString(csales));
                GDSales.setText(Integer.toString(gsales));
                B182Sales.setText(Integer.toString(bsales));
            }   catch (FileNotFoundException ex) 
            {
                Logger.getLogger(AgentSalesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(AgentSalesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) 
            {
                Logger.getLogger(AgentSalesController.class.getName()).log(Level.SEVERE, null, ex);
            }   
        });
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateChoiceBox();
            choiceBoxChangeValueAction();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgentSalesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AgentSalesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     
    
}
