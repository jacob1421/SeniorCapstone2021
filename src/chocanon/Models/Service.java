/* 
    File: Service.java
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
    Description: Models the data for the services that come from the database
 */

package chocanon.Models;
import Database.DatabaseConnector;
import Logger.Log;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class Service {
    //Data attributes
    private String name = "";
    private int code = 0;
    private BigDecimal fee = null;
    
    //Constructors
    public Service(){
    }
    
    public Service(String name, int code, BigDecimal fee){
        this.name = name;
        this.code = code;
        this.fee = fee;
    }
    
    //Getters
    public String getServiceName(){
        return this.name;
    }
    
    public int getServiceCode(){
        return this.code;
    }
    
    public BigDecimal getServiceFee(){
        return this.fee;
    }
    
    //Static getters
    public static Service getServiceByServiceId(int serviceId){
        Service serviceFound = null;
        Log.debug("Service", "Service Id: " + serviceId);
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("SELECT name, fee, code FROM chocanon_db.services WHERE service_id = %s LIMIT 1;", serviceId);
            Log.debug("Service", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            //Get Results
            while (rset.next()) {
                 serviceFound = new Service(rset.getString("name"), rset.getInt("code"), rset.getBigDecimal("fee"));
            }
            
            //Print the provider found
            if(serviceFound != null){
                Log.debug("Service", "Service Found: " + serviceFound.toString());
            }else{
                Log.debug("Service", "No Service Found With Service Id: " + serviceId);
            }
            
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Service", ex.toString());
        }    
        return serviceFound;
    }
    
    public static Service[] getAllServices(){
        ArrayList<Service> allServices = new ArrayList<>(); 
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("SELECT name, fee, code FROM chocanon_db.services;");
            Log.debug("Service", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            //Get Results
            while (rset.next()) {
                //Build the service
                Service s = new Service(rset.getString("name"), rset.getInt("code"), rset.getBigDecimal("fee"));
                Log.debug("Service", "Found Service: " + s.toString());
                //Add Service
                allServices.add(s);
            }
            
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Service", ex.toString());
        }  
        
        return Arrays.stream(allServices.toArray()).toArray(Service[]::new);
    }
    
    @Override
    public String toString(){
        return ("Service Name: " + this.getServiceName() + " Code: " + this.getServiceCode() + " Fee: " + this.getServiceFee()+ "\n");
    }
        
}
