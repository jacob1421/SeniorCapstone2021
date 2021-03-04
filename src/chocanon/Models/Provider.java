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

import Database.DatabaseConnector;
import Logger.Log;
import java.sql.*;  
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static Provider getProviderByProviderNumber(int providerNumber){
        Log.debug("Provider", "Provider Number: " + providerNumber);
        Provider providerFound = null;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("SELECT provider_id, first_name, last_name, street_address, city, state, zip_code, provider_number, providertypes.provider_type, provider_email FROM providers JOIN providertypes ON providers.provider_type_id = providertypes.provider_type_id WHERE provider_number = %s LIMIT 1;", providerNumber);
            Log.debug("Provider", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            //Get Results
            while (rset.next()) {
                 providerFound = new Provider(rset.getInt("provider_id"), rset.getString("first_name"), rset.getString("last_name"), rset.getString("street_address"), rset.getString("city"), rset.getString("state"), rset.getInt("zip_code"), rset.getInt("provider_number"), rset.getString("provider_type"), rset.getString("provider_email"));
            }
            
            //Print the provider found
            if(providerFound != null){
                Log.debug("Provider", "Provider Found: " + providerFound.toString());
            }else{
                Log.debug("Provider", "No Provider Found With Provider Number: " + providerNumber);
            }
            
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Provider", ex.toString());
        }    
        return providerFound;
    }
    
    public static Provider getProviderByProviderDbId(int providerDatabaseId){
        Log.debug("Provider", "Provider Id " + providerDatabaseId);
        Provider providerFound = null;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("SELECT provider_id, first_name, last_name, street_address, city, state, zip_code, provider_number, providertypes.provider_type, provider_email FROM providers JOIN providertypes ON providers.provider_type_id = providertypes.provider_type_id WHERE provider_id = %s LIMIT 1;", providerDatabaseId);
            Log.debug("Provider", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            //Get Results
            while (rset.next()) {
                 providerFound = new Provider(rset.getInt("provider_id"), rset.getString("first_name"), rset.getString("last_name"), rset.getString("street_address"), rset.getString("city"), rset.getString("state"), rset.getInt("zip_code"), rset.getInt("provider_number"), rset.getString("provider_type"), rset.getString("provider_email"));
            }
            
            //Print the provider found
            if(providerFound != null){
                Log.debug("Provider", "Provider Found: " + providerFound.toString());
            }else{
                Log.debug("Provider", "No Provider Found With Provider Id: " + providerDatabaseId);
            }
            
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Provider", ex.toString());
        }    
        return providerFound;
    }
    
    
    @Override
    public String toString(){
        return ("Database Id: " + this.getDatabaseId() + " First Name: " + this.getFirstName() + " Last Name: " + this.getLastName() + " Street Address: " + this.getStreetAddress() + " City: " + this.getCity() + " State: " + this.getState() + " Zip Code: " + this.getZipCode() + " Provider Number: " + this.getProviderNumber() + " Provider Type: " + this.getProviderType() + " Provider Email: " + this.getEmailAddress() + "\n");
    }
    
}