/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMS;

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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author joldf_000
 */
public class ClientListController implements Initializable{
    
    @FXML Label GDQtySold, GDQtyRem, CFQtySold, CFQtyRem, B182QtySold, B182QtyRem, GDDate, CFDate, B182Date;
    
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
    public void populateTable() throws ClassNotFoundException, IOException{
        
        JJT jjt = null;
        try {
            jjt = new JJT();
        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Event> eventList = jjt.readListOfEvents();
        ArrayList<Agent> agentList = jjt.readListOfAgents();
        
        for(Event e:eventList){
            if(e.getId() == 1)
            {
                GDQtyRem.setText(Integer.toString(e.getQuantity()));
                    int gdsales;
                    gdsales = 0;
                for(Agent a:agentList){
                    int s = a.getSalesForGD();
                    gdsales = (gdsales + s);
                }
                GDQtySold.setText(Integer.toString(gdsales));
                GDDate.setText(e.getDate());
                
            }
            if(e.getId() == 2)
            {
                CFQtyRem.setText(Integer.toString(e.getQuantity()));
                    int cfsales;
                    cfsales = 0;
                for(Agent a:agentList){
                    int s = a.getSalesForCF();
                    cfsales = (cfsales + s);
                    CFQtySold.setText(Integer.toString(cfsales));
                    CFDate.setText(e.getDate());
                }
                
            }
            if(e.getId() == 3)
            {
                B182QtyRem.setText(Integer.toString(e.getQuantity()));
                    int b182sales;
                    b182sales = 0;
                for(Agent a:agentList){
                    int s = a.getSalesForB182();
                    b182sales = (b182sales + s);
                    B182QtySold.setText(Integer.toString(b182sales));
                    B182Date.setText(e.getDate());
                }
                
            }   
        }
        
     
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources){
        try {
            populateTable();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AgentSalesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
