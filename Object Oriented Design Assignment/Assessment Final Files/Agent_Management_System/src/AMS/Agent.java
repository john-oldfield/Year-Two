/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AMS;

import java.io.Serializable;

/* @author John Oldfield */

public class Agent implements Serializable
{
    private int id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String phone;
    public boolean active;
    private int salesForGD;
    private int salesForCF;
    private int salesForB182;

    public Agent(int id, String username, String password, String name, String 
            address, String phone, int sales, boolean active, int salesForGD, int salesForCF, int salesForB182)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.active = active;
        this.salesForGD = salesForGD;
        this.salesForCF = salesForCF;
        this.salesForB182 = salesForB182;
    }
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getUsername()
    {
        return username;
    }
    
    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    
    public boolean getActive()
    {
        return this.active;
    }
    
    public void setActive(boolean active)
    {
        this.active = active;
    }
    
    public void setSalesForGD(int salesForGD){
        this.salesForGD = salesForGD;
    }
    
    public int getSalesForGD()
    {
        return salesForGD;
    }
    
    public void setSalesForCF(int salesForCF){
        this.salesForCF = salesForCF;
    }
    
    public int getSalesForCF()
    {
        return salesForCF;
    }
    
    public void setSalesForB182(int salesForB182){
        this.salesForB182 = salesForB182;
    }
    
    public int getSalesForB182()
    {
        return salesForB182;
    }
    
//    @Override
//    public String toString()
//    {
//        return "id="+id+"Username="+username+"Password="+password+"Name="+name+
//                "Address="+address+"Phone="+phone+"Sales="++"Active="+active;
//    }
}