/* 
    File: Provider.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 2/13/2021
    Updated by: Jacob Bremiller
    Updated: 2/25/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: Class used to model the data for the providers that are pulled from the database.
 */
package chocanon.Models;

public class Provider extends Person{
    //Data Attributes
    private int providerNumber = 0;
    private String providerType = "";
    private String providerEmail = "";
    
    //Constructor
    public Provider(String firstName, String lastName, String streetAddress, String city, String state, int zipCode, int providerNumber, String providerType, String providerEmail){
        super(firstName, lastName, streetAddress, city, state, zipCode);
        this.providerNumber = providerNumber;
        this.providerType = providerType;
        this.providerEmail = providerEmail;
    }
    
    //Getters
    public int getProviderNumber(){
        return this.providerNumber;
    }
    
    public String getProviderType(){
        return this.providerType;
    }
    
    public String getProviderEmail(){
        return this.providerEmail;
    }
    
    @Override
    public String toString(){
        return ("First Name: " + this.getFirstName() + " Last Name: " + this.getLastName() + " Street Address: " + this.getStreetAddress() + " City: " + this.getCity() + " State: " + this.getState() + " Zip Code: " + this.getZipCode() + " Provider Number: " + this.getProviderNumber() + " Provider Type: " + this.getProviderType() + " Provider Email: " + this.getProviderEmail() + "\n");
    }
    
}