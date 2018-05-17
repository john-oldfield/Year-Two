
package AMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2oldfj45
 */
public class JJT implements Serializable
{
   //Arrays for Agent and Events. 
    ArrayList<Agent> agentList;    
    ArrayList<Event> eventList;

    public JJT() throws FileNotFoundException, ClassNotFoundException, IOException{
        
        try {
            File file = new File("agents.txt");
            
            if(file.exists())
            {
                agentList = readListOfAgents();
                
            }
            else
            {
                agentList = new ArrayList<>();
                setAgentsList();
                agentList = readListOfAgents();
                
            }
        }catch (IOException ex) 
        {
            Logger.getLogger(JJT.class.getName()).log(Level.SEVERE, null, ex);
        }
            try{
                File file2 = new File("events.txt");
                if(file2.exists()){
                eventList = readListOfEvents();
            }
            else{
                eventList = new ArrayList<>();
                setEventsList();
                eventList = readListOfEvents();
                
            }
                
  
        } catch (IOException ex) {
            Logger.getLogger(JJT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void setEventsList() throws IOException{
        eventList.add(new Event(1,"Green Day", "Southampton", "11/01/2017", 100));
        eventList.add(new Event(2, "Catfish and the Bottlemen", "Nottingham", "12/01/2017", 50));
        eventList.add(new Event(3, "Blink 182", "London", "13/01/2017", 80));
        
        saveListOfEvents(eventList);
    }

    public ArrayList<Agent> getAgentList() {
        return agentList;
    }
    
    
    public void setAgentsList() throws IOException
    {
        agentList.add(new Agent(1,"JOldfield1","pass", "John Oldfield", 
                "1 Bronte Close", "07411356021", 0, false, 0, 0, 0));
        agentList.add(new Agent(2,"CAustin10","pass", "Charlie Austin", 
                "2 Bronte Close", "07411356022", 0, false, 0, 0, 0));
        agentList.add(new Agent(3,"FForster1","pass", "Fraser Forster", 
                "3 Bronte Close", "07411356023", 0, false, 0, 0, 0));
        
        saveListOfAgents(agentList);
    }
    
    public void updateAgent(Agent agent) throws IOException
    {
        for(Agent a:agentList)
        {
            if(a.getUsername().equals(agent.getUsername()))
            {
                a.setActive(true);
            }
        }
        saveListOfAgents(agentList);
    }
    
    public Agent getCurrentActive() throws IOException, FileNotFoundException, 
            ClassNotFoundException{
        agentList = readListOfAgents();
        for(Agent a:agentList){
            if(a.getActive() == true)
            {
                return a;
            }
        }
        return null;
    }
    // Event File Handling Block
    public void saveListOfEvents(ArrayList<Event> eventsList) throws 
            FileNotFoundException, IOException
    {
        FileOutputStream fos = new FileOutputStream("events.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(eventsList);
        oos.close();
    }
    
    public ArrayList<Event> readListOfEvents() throws FileNotFoundException, 
            IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream("events.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Event> listOfEvents = (ArrayList<Event>) ois.readObject();
        ois.close();
        return listOfEvents;        
    }
    
    
            
    // Agent File Handling Block
    public void saveListOfAgents(ArrayList<Agent> agentsList) throws 
            FileNotFoundException, IOException
    {
        FileOutputStream fos = new FileOutputStream("agents.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(agentsList);
        oos.close();
    }
    
    public ArrayList<Agent> readListOfAgents() throws FileNotFoundException, 
            IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream("agents.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Agent> listOfAgents = (ArrayList<Agent>) ois.readObject();
        ois.close();
        return listOfAgents;
    }
    
    public Agent returnAgentBasedOnUsername(String username) throws 
            FileNotFoundException, ClassNotFoundException, IOException
    {
        
        ArrayList<Agent> agentList = readListOfAgents();
        
        for(Agent a:agentList)
        {
            if(a.getUsername().equals(username))
            {
                return a;
            }
        }
        return null;
        
    }
    public Agent returnAgentBasedOnId(int id) throws IOException, ClassNotFoundException{
        ArrayList<Agent> agentList = readListOfAgents();
        
        for(Agent a:agentList)
        {
            if(a.getId() == id)
            {
                return a;
            }
        }
        return null;
    }
    
    public Event returnEventBasedOnName(String name) throws IOException, FileNotFoundException, ClassNotFoundException
    {
        ArrayList<Event> eventList = readListOfEvents();
        
        for(Event e:eventList)
        {
            if(e.getName().equals(name))
            {
                return e;
            }
        }
        return null;
    }
    
    
        
}
    
