/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMS;

import java.io.Serializable;

/**
 *
 * @author joldf_000
 */
public class Event implements Serializable 
{
    private int id;
    private String name;
    private String location;
    private String date;
    private int quantity;
    
    public Event(int id, String name, String location, String date, int quantity)
    {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.quantity = quantity;
   
    }
    
    public int getId()
    {
        return id;        
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;        
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getLocation()
    {
        return location;        
    }
    
    public void setLocation(String location)
    {
        this.location = location;
    }
    
    public String getDate()
    {
        return date;        
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public int getQuantity()
    {
        return quantity;        
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
}