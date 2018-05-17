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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 *
 * @author 2oldfj45
 */
public class DeleteTicketController implements Initializable{
    
    @FXML
    ChoiceBox events, agents;
    
    @FXML
    Slider quantity;
    
    @FXML Button submit;
    
    @FXML Label status;
    
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
     public void populateChoiceBox() throws FileNotFoundException, ClassNotFoundException, IOException
     {
        JJT jjt = new JJT();
        ArrayList<Event> eventList = jjt.readListOfEvents();
        ArrayList<Agent> agentList = jjt.readListOfAgents();
        
        for(Event e:eventList)
            {
                events.getItems().add(e.getName());
            }
        for(Agent a:agentList)
        {
            agents.getItems().add(a.getUsername());
        }
    }
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateChoiceBox();
            choiceBoxChangeValueAction();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DeleteTicketController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DeleteTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void choiceBoxChangeValueAction()
    {
        agents.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> 
        {            
            JJT jjt;
            try {
                jjt = new JJT();
            Agent agent = jjt.returnAgentBasedOnUsername((String) newValue);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DeleteTicketController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DeleteTicketController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DeleteTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        events.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> 
        {            
            JJT jjt;
            try {
                jjt = new JJT();
            Event event = jjt.returnEventBasedOnName((String) newValue);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DeleteTicketController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DeleteTicketController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DeleteTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
     
     public void deleteTicket() throws ClassNotFoundException, IOException{
         JJT jjt = new JJT();
         ArrayList<Event> eventList = jjt.readListOfEvents();
         ArrayList<Agent> agentList = jjt.readListOfAgents();
         String eventName = (String) events.getValue();
         String agentName = (String) agents.getValue();
         int tickets = (int) quantity.getValue();
         for(Event e:eventList){
             if(e.getName().equals(eventName)){
                 int oldQuantity = e.getQuantity();
                 int newQuantity = (oldQuantity + tickets);
                 e.setQuantity(newQuantity);
                 for(Agent a:agentList){
                     if(a.getUsername().equals(agentName))
                     {
                         if(e.getId() == 1)
                         {
                             int oldSales = a.getSalesForGD();
                             int newSales = (oldSales - tickets);
                             a.setSalesForGD(newSales);
                             
                         }
                         if(e.getId() == 2)
                         {
                             int oldSales = a.getSalesForCF();
                             int newSales = (oldSales - tickets);
                             a.setSalesForCF(newSales);
                         }
                         if(e.getId() == 3)
                         {
                             int oldSales = a.getSalesForB182();
                             int newSales = (oldSales - tickets);
                             a.setSalesForB182(newSales);
                         }
                         
                     }
                 }
             }
         }
         jjt.saveListOfAgents(agentList);
         jjt.saveListOfEvents(eventList);
         if(tickets == 1){
             status.setText(tickets+" Ticket sale cancelled for: "+eventName+". Sales removed from Agent: "+agentName+".");
         }
         if(tickets > 1){
         status.setText(tickets+" Ticket sales cancelled for: "+eventName+". Sales removed from Agent: "+agentName+".");
         }
         
         
         
         
     }
    
}
