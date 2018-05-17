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
public class CreateTicketController implements Initializable
{
    @FXML ChoiceBox events;
    
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
        
        for(Event e:eventList)
            {
                events.getItems().add(e.getName());
            }
    }
     
    public void choiceBoxChangeValueAction()
    {
        events.getSelectionModel().selectedItemProperty().addListener( (v, oldValue, newValue) -> 
        {            
            JJT jjt;
            try {
                jjt = new JJT();
            Event event = jjt.returnEventBasedOnName((String) newValue);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CreateTicketController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CreateTicketController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CreateTicketController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            populateChoiceBox();
            choiceBoxChangeValueAction();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateTicketController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CreateTicketController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sellTicket() throws ClassNotFoundException, IOException{
        JJT jjt = new JJT();
        ArrayList<Event> eventList = jjt.readListOfEvents();
        ArrayList<Agent> agentList = jjt.readListOfAgents();
        int ticketQuantity = (int) quantity.getValue();
        String event = (String) events.getValue();
        for(Event e:eventList){
            if(e.getName().equals(event)){
                int qty = e.getQuantity();
                System.out.println("Old Qty: "+qty);
                int newQty = (qty - ticketQuantity);
                System.out.println("New Qty:" +newQty);
                e.setQuantity(newQty);
                for(Agent a:agentList){
                    if(a.getActive() == true){
                        if(e.getId() == 1){
                            int oldSales = a.getSalesForGD();
                            int newSales = (oldSales + ticketQuantity);
                            a.setSalesForGD(newSales);
                            }
                        if(e.getId() == 2){
                            int oldSales = a.getSalesForCF();
                            int newSales = (oldSales + ticketQuantity);
                            a.setSalesForCF(newSales);  
                        }
                        if(e.getId() == 3){
                            int oldSales = a.getSalesForB182();
                            int newSales = (oldSales + ticketQuantity);
                            a.setSalesForB182(newSales);
                        }
                    }
                }
                
            }
            
        }
        jjt.saveListOfEvents(eventList);
        jjt.saveListOfAgents(agentList);
        status.setText(ticketQuantity+" Tickets sold for: "+event+".");
        
    }
    
     
     
     
}
