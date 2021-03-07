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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Provider extends Person{
    //Data Attributes
    private int providerNumber = 0;
    private String providerType = "";
    private int databaseId = 0;
    
    public static Object[][] providerTypes = null;
    
    //Constructors
    public Provider(){
    }
    
    public Provider(String firstName, String lastName, String streetAddress, String city, String state, int zipCode, int providerNumber, String providerType, String emailAddress){
        super(firstName, lastName, streetAddress, city, state, zipCode, emailAddress);
        this.providerNumber = providerNumber;
        this.providerType = providerType;
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
    public void setProviderNumber(int providerNumber){
        this.providerNumber = providerNumber;
    }
    
    public void setProviderType(String providerType){
        this.providerType = providerType;
    }
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
            Logger.getLogger(Provider.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return providerFound;
    }
    
    public static Provider[] getProvidersByNameOrProviderNumberOrType(String searchText){
        Log.debug("Provider", "Search Text: " + searchText);
        ArrayList<Provider> providersFound = new ArrayList<>(); 
         try {
             //Create Database Connection
             DatabaseConnector dbConn = new DatabaseConnector();
             Connection conn = dbConn.getDatabaseConnection();
             Statement stmt = conn.createStatement();
             //Query
             searchText = "%" + searchText + "%";
             String strSelect = String.format("SELECT provider_id, first_name, last_name, street_address, city, state, zip_code, provider_number, provider_email, providertypes.provider_type FROM providers JOIN providertypes ON providers.provider_type_id = providertypes.provider_type_id WHERE (first_name LIKE('%s') OR last_name LIKE('%s') OR provider_number LIKE('%s') OR providertypes.provider_type LIKE('%s')) AND deleted = 0 LIMIT 25;", searchText, searchText, searchText, searchText);
             Log.debug("Provider", "Query: " + strSelect);
             //Execute Query
             ResultSet rset = stmt.executeQuery(strSelect);
             //Get Results
             while (rset.next()) {
                 //Build the provider
                 Provider p = new Provider(rset.getInt("provider_id"), rset.getString("first_name"), rset.getString("last_name"), rset.getString("street_address"), rset.getString("city"), rset.getString("state"), rset.getInt("zip_code"), rset.getInt("provider_number"), rset.getString("provider_type"), rset.getString("provider_email"));
                 Log.debug("Provider", "Found Provider: \n" + p.toString());
                 //Add Provider
                 providersFound.add(p);
             }

             if(providersFound.size() > 0){
                 Log.debug("Provider", "Found " + providersFound.size() + " Providers");
             }else{
                 Log.debug("Provider", "No Providers Found With Search Text: " + searchText);
             }

             //Close database
             dbConn.closeDatabaseConnection();
         } catch (Exception ex) {
             Log.error("Provider", ex.toString());
         }  

         return Arrays.stream(providersFound.toArray()).toArray(Provider[]::new);
    }
    
    public static int deleteProviderByDatabaseId(int databaseId){
        int affectedRows = 0;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("UPDATE chocanon_db.providers SET deleted = 1 WHERE provider_id = %s", databaseId);
            Log.debug("Provider", "Query: " + strSelect);
            //Execute Query
            affectedRows = stmt.executeUpdate(strSelect);
            if(affectedRows > 0){
                Log.debug("Provider", affectedRows + " rows were affected.");
            }else{
                Log.debug("Provider", "Delete From Database Failed..");
            }
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Provider", ex.toString());
        }  
        return affectedRows;
    }
    
    public static Object[][] getAllProviderTypes(){
        ArrayList<Object[]> providerTypes = new ArrayList<>(); 
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = "SELECT provider_type_id, provider_type FROM chocanon_db.providertypes";
            Log.debug("Provider", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            //Get Results
            while (rset.next()) {
                //Build the provider
                Object[] provType = new Object[]{
                    rset.getInt("provider_type_id"),
                    rset.getString("provider_type")
                };
                Log.debug("Provider", "Found Provider Type: " + (String)provType[1]);
                //Add Provider
                providerTypes.add(provType);
            }

            if(providerTypes.size() > 0){
                Log.debug("Provider", "Found " + providerTypes.size() + " Provider Types");
            }else{
                Log.debug("Provider", "No Provider Types");
            }

            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Provider", ex.toString());
        }  

        return Arrays.stream(providerTypes.toArray()).toArray(Object[][]::new);
    }
    
   
    public static boolean doesProviderNumberExist(String providerNumber){
        boolean cardExists = false;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("SELECT count(*) as provider_number_exist FROM chocanon_db.providers WHERE provider_number = %s", providerNumber);
            Log.debug("Provider", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            while (rset.next()) {
                cardExists = rset.getInt("provider_number_exist") != 0;
            }  
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Provider", ex.toString());
        }  
        return cardExists;
    }
        
    public static int insertNewProvider(Provider prov){
        int affectedRows = 0;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("INSERT INTO chocanon_db.providers(first_name, last_name, street_address, city, state, zip_code, provider_email, provider_number, provider_type_id) VALUES('%s','%s','%s','%s','%s','%s','%s','%s','%s')", prov.getFirstName(), prov.getLastName(), prov.getStreetAddress(), prov.getCity(), biTranslateState(prov.getState(), "ABBR"), prov.getZipCode(), prov.getEmailAddress(), prov.getProviderNumber(), findProviderTypeIdByTypeString(prov.getProviderType()));
            Log.debug("Provider", "Query: " + strSelect);
            //Execute Query
            affectedRows = stmt.executeUpdate(strSelect);
            if(affectedRows > 0){
                Log.debug("Provider", affectedRows + " rows were affected.");
            }else{
                Log.debug("Provider", "Adding New Provider To Database Failed..");
            }
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Provider", ex.toString());
        }  
        return affectedRows;
    }
    
        public static int updateNewProvider(Provider prov){
        int affectedRows = 0;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("UPDATE chocanon_db.providers SET first_name = '%s', last_name = '%s', street_address = '%s', city = '%s', state = '%s', zip_code = %s, provider_email = '%s', provider_number = %s, provider_type_id = %s WHERE provider_id = %s;", prov.getFirstName(), prov.getLastName(), prov.getStreetAddress(), prov.getCity(), prov.getState(), prov.getZipCode(), prov.getEmailAddress(), prov.getProviderNumber(), findProviderTypeIdByTypeString(prov.getProviderType()), prov.getDatabaseId());
            Log.debug("Provider", "Query: " + strSelect);
            //Execute Query
            affectedRows = stmt.executeUpdate(strSelect);
            if(affectedRows > 0){
                Log.debug("Provider", affectedRows + " rows were affected.");
            }else{
                Log.debug("Provider", "Updating Provider In Database Failed..");
            }
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Provider", ex.toString());
        }  
        return affectedRows;
    }
    
    //Search function
    public static Provider findProviderById(Provider[] provArray, int targetDatabaseId){
        Provider foundProv = null;
        for(int i = 0;i < provArray.length;i++){
            if(provArray[i].getDatabaseId() == targetDatabaseId){
                foundProv = provArray[i];
                break;
            }
        }
        return foundProv;
    }
    public static int findProviderTypeIdByTypeString(String targetProviderType){
        int foundProviderId = -1;
        for(int i = 0;i < providerTypes.length;i++){
            if(providerTypes[i][1] == targetProviderType){
                foundProviderId = (Integer)providerTypes[i][0];
                break;
            }
        }
        return foundProviderId;
    }
    
    @Override
    public String toString(){
        return ("Database Id: " + this.getDatabaseId() + " First Name: " + this.getFirstName() + " Last Name: " + this.getLastName() + " Street Address: " + this.getStreetAddress() + " City: " + this.getCity() + " State: " + this.getState() + " Zip Code: " + this.getZipCode() + " Provider Number: " + this.getProviderNumber() + " Provider Type: " + this.getProviderType() + " Provider Email: " + this.getEmailAddress() + "\n");
    }
    
}