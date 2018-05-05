/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joldf_000
 */
public class ServiceValidityController implements Initializable {
    
    @FXML ChoiceBox events;
    
    @FXML DatePicker date;
    
    
    @FXML Label confirmation;
    
    @FXML Button home, submit;
    
    
    
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
                Logger.getLogger(ServiceValidityController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException | IOException ex) {
                Logger.getLogger(ServiceValidityController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
    }
    @FXML public void setValidSaleDate() throws ClassNotFoundException, IOException{
        JJT jjt = new JJT();
        ArrayList<Event> eventList = jjt.readListOfEvents();
        String event = (String) events.getValue(); 
        
        for(Event e:eventList)
        {
            if(e.getName().equals(event)){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                String formattedString = date.getValue().format(formatter);
                e.setDate(formattedString);
                confirmation.setText("Ticket sales for "+event+" are now valid until: "+formattedString+".");
            }
            
        }
        jjt.saveListOfEvents(eventList);
        
    
    }

    /**
     * initialises the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populateChoiceBox();
            choiceBoxChangeValueAction();
        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(ServiceValidityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
