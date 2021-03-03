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
    Updated: 3/3/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: Class used to model the data for the providers that are pulled from the database.
 */
package chocanon.Models;

public class Provider extends Person{
    //Data Attributes
    private int providerNumber = 0;
    private String providerType = "";
    private int databaseId = 0;
    
    //Constructors
    public Provider(){
    }
    
    public Provider(int databaseId, String firstName, String lastName, String streetAddress, String city, String state, int zipCode, int providerNumber, String providerType, String emailAddress){
        super(firstName, lastName, streetAddress, city, state, zipCode, emailAddress);
        this.providerNumber = providerNumber;
        this.providerType = providerType;
        this.databaseId = databaseId;
    }
    
    //Getters
    public int getProviderNumber(){
        return this.providerNumber;
    }
    
    public String getProviderType(){
        return this.providerType;
    }
    
    public int getDatabaseId(){
        return this.databaseId;
    }
    
    //Setters
    public void setDatabaseId(int databaseId){
       this.databaseId = databaseId;
    }
        
    //Static getters
    public static Provider getProviderByProviderId(int providerId){
        Provider providerFound = null;
        /* Do the database functionality to get a provider from the database by provider id - 9 digit*/
        return providerFound;
    }
    
    @Override
    public String toString(){
        return ("Database Id: " + this.getDatabaseId() + " First Name: " + this.getFirstName() + " Last Name: " + this.getLastName() + " Street Address: " + this.getStreetAddress() + " City: " + this.getCity() + " State: " + this.getState() + " Zip Code: " + this.getZipCode() + " Provider Number: " + this.getProviderNumber() + " Provider Type: " + this.getProviderType() + " Provider Email: " + this.getEmailAddress() + "\n");
    }
    
}