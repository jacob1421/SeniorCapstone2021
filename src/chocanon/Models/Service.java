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
import java.math.BigDecimal;

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
        /* Do the database functionality to get a service from the database by service code*/
        return serviceFound;
    }
    
    @Override
    public String toString(){
        return ("Service Name: " + this.getServiceName() + " Code: " + this.getServiceCode() + " Fee: " + this.getServiceFee()+ "\n");
    }
        
}
