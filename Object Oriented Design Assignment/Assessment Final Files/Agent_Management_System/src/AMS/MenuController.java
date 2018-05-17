/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMS;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author joldf_000
 */


public class MenuController implements Initializable, Serializable
{    
    @FXML private Text activeUser;
    @FXML private Text inactiveUser;
    JJT jjt;
    ArrayList<Agent> agentList;
    
    @FXML Button createAgent, modifyAgent, deleteAgent, agentSales, createTicket, deleteTicket, clientList, ticketValidity;
    @FXML Label description, description1;
    
    public MenuController() throws FileNotFoundException, ClassNotFoundException, IOException
    {
        jjt = new JJT();
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetDescription();
        try {
            agentList = jjt.readListOfAgents();
        for(Agent a:agentList)
        {
            if(a.getActive() == true)
            {
                activeUser.setText(a.getUsername());
            }
            else{
                if(inactiveUser.getText().length() == 0)
                {
                    inactiveUser.setText(a.getUsername());
                }
                else
                {
                    String existing = inactiveUser.getText();
                    existing += "\n";
                    existing += a.getUsername();
                    inactiveUser.setText(existing);
                }
        }
        
        }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
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
    
    @FXML public void Logout(ActionEvent event) throws Exception
        {
            Agent a = jjt.getCurrentActive();
            agentList = jjt.readListOfAgents();
            for(Agent ag:agentList){
                if(ag.getUsername().equals(a.getUsername())){
                    ag.setActive(false);
                }
            }
            jjt.saveListOfAgents(agentList);
            
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        
        for(Agent b:agentList){
            System.out.println(b.getUsername()+":"+b.getActive());
               
        }
        }
    
    public void GoToCreateAgent(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CreateAgent.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
    
    @FXML public void GoToDeleteAgent(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DeleteAgent.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
    
    @FXML 
    public void GoToModifyAgent(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModifyAgent.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
    
    @FXML public void GoToAgentSales(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AgentSales.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
    
    @FXML public void GoToCreateTicket(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("CreateTicket.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
    
    @FXML public void GoToDeleteTicket(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("DeleteTicket.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
    
    @FXML public void GoToModifyTicket(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ModifyTicket.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
    @FXML public void GoToTicketValidity(ActionEvent event) throws Exception
    {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ServiceValidity.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
        
    
    @FXML public void GoToClientList(ActionEvent event) throws Exception
        {
        ((Node)event.getSource()).getScene().getWindow().hide(); 
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ClientList.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("JJT Agent Management System");
        stage.setScene(scene);
        stage.show();
        }
    public void resetDescription()
    {
        description.setText("For help hover over a button.");
        description1.setText("For help hover over a button.");
    }
    
    public void displayCADescription()
    {
        description.setText("Select this option to create a new agent.");
        
    }
    
    public void displayMADescription(){
        description.setText("Select this option to modify an agent.");
    }
    
    public void displayDADescription(){
        description.setText("Select this option to delete an agent.");
    }
    
    public void displayASDescription(){
        description.setText("Select this option to view agent sales information.");
    }
    
    public void displayCTDescription(){
        description1.setText("Select this option to make a ticket sale.");
        
    }
    
    public void displayDTDescription(){
        description1.setText("Select this option to cancel a ticket sale.");
    }
    
    public void displayTSDescription(){
        description1.setText("Select this option to view ticket sales information.");
    }
    
    public void displayTVDescription(){
        description1.setText("Select this option to set the valid sale dates for an event.");
    }
    
}