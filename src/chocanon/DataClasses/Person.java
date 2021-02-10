/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanon.DataClasses;

/**
 *
 * @author jakeb
 */
public class Person {
    //Data Attributes
    private String firstName = "";
    private String lastName = "";
    private String streetAddress = "";
    private String city = "";
    private String state = "";
    private int zipCode = 0;
    
    //Constructor
    public Person(String firstName, String lastName, String streetAddress, String city, String state, int zipCode){
       this.firstName = firstName;
       this.lastName = lastName;
       this.streetAddress = streetAddress;
       this.city = city;
       this.state = state;
       this.zipCode = zipCode;
    }
    
    //Getters
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public String getStreetAddress(){
        return this.streetAddress;
    }
    
    public String getCity(){
        return this.city;
    }
    
    public String getState(){
        return this.state;
    }
    
    public int getZipCode(){
        return this.zipCode;
    }
    
}
