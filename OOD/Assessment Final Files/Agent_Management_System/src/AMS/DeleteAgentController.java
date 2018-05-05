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

/**
 *
 * @author 2oldfj45
 */
public class DeleteAgentController implements Initializable{
    
    @FXML
    ChoiceBox<String> agents, agents2;
        
    @FXML
    Label userDescription, agentName, agentSales, confirmation;
    
    
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
    
    
    public void populateChoiceBox() throws FileNotFoundException, ClassNotFoundException, IOException{
        JJT jjt = new JJT();
        ArrayList<Agent> agentList = jjt.readListOfAgents();
        for(Agent a:agentList){
            agents.getItems().add(a.getUsername());
            agents2.getItems().add(a.getUsername());
    }
    }
    
    public void DeleteAgent() throws FileNotFoundException, ClassNotFoundException, IOException{
        JJT jjt = new JJT();
        ArrayList<Agent> agentList = jjt.readListOfAgents();
        String usrname = agents.getValue();
        String usrname2 = agents2.getValue();
        for(Agent a:agentList){
            if(a.getUsername().equals(usrname)){
                int gSales = a.getSalesForGD();
                int csales = a.getSalesForCF();
                int bsales = a.getSalesForB182();
                for(Agent b:agentList){
                    if(b.getUsername().equals(usrname2)){
                        int g2sales = b.getSalesForGD();
                        int c2sales = b.getSalesForCF();
                        int b2sales = b.getSalesForB182();
                        int newGSales = (gSales+g2sales);
                        int newCSales = (csales+c2sales);
                        int newBSales = (bsales + b2sales);
                        b.setSalesForGD(newGSales);
                        b.setSalesForCF(newCSales);
                        b.setSalesForB182(newBSales);
                    }
                }
                
                agentList.remove(a);
                confirmation.setText("Agent Deleted Successfully. Sales past onto "+usrname2+".");
                break;
            }
        }
        jjt.saveListOfAgents(agentList);
    }
    
    public void choiceBoxChangeValueAction()
    {
        agents.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> 
        {            
            JJT jjt;
            try {
                jjt = new JJT();
            Agent agent = jjt.returnAgentBasedOnUsername(newValue);
            agentName.setText(agent.getName());
            int gsales = agent.getSalesForGD();
            int csales = agent.getSalesForCF();
            int bsales = agent.getSalesForB182();
            int totalSales = (gsales + csales + bsales);
            agentSales.setText(Integer.toString(totalSales));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DeleteAgentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DeleteAgentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DeleteAgentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        agents2.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> 
        {            
            JJT jjt;
            try {
                jjt = new JJT();
            Agent agent = jjt.returnAgentBasedOnUsername(newValue);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DeleteAgentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DeleteAgentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DeleteAgentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateChoiceBox();
            choiceBoxChangeValueAction();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteAgentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DeleteAgentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    
}
