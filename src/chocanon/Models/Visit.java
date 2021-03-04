/* 
    File: Visit.java
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
    Description: Models the visit data that is pulled from the database.
 */
package chocanon.Models;
import Database.DatabaseConnector;
import Logger.Log;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class Visit {
    //Date Format
    SimpleDateFormat currentDateTimeFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss", Locale.ENGLISH);
    SimpleDateFormat dateOfServiceFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
    
    //Data Attributes
    private Date receivedVisitDateTime = null; //Date and time that the visit was recieved by chocanon
    private Date dateOfService = null; //Date that the visit was created
    private Provider providerInfo = null;
    private Member memberInfo = null;
    private Service serviceInfo = null;
    private String comment = "";
    private int databaseId = 0;
    
    //Constructors
    public Visit(){
    
    }
    
    //Constructor for saving a visit
    public Visit(Provider providerInfo, Member memberInfo, Service serviceInfo, String dateOfService, String comment) throws ParseException{
        this.providerInfo = providerInfo;
        this.memberInfo = memberInfo;
        this.serviceInfo = serviceInfo;
        this.comment = comment;
        this.dateOfService = dateOfServiceFormatter.parse(dateOfService);
    }
    
    //Constructor for loading a visit
    public Visit(int databaseId, Provider providerInfo, Member memberInfo, Service serviceInfo, String dateOfService, String receivedVisitDateTime, String comment) throws ParseException{
        this.providerInfo = providerInfo;
        this.memberInfo = memberInfo;
        this.serviceInfo = serviceInfo;
        this.comment = comment;
        this.dateOfService = dateOfServiceFormatter.parse(dateOfService);
        this.receivedVisitDateTime = currentDateTimeFormatter.parse(receivedVisitDateTime);
        this.databaseId = databaseId;
    }
    
    //Getters
    public String getVisitDate(){
        return dateOfServiceFormatter.format(this.dateOfService);
    }
    
    public String getReceivedVisitDateTime(){
        return currentDateTimeFormatter.format(this.receivedVisitDateTime);
    }
    
    public Provider getProviderInfo(){
        return this.providerInfo;
    }
    
    public Member getMemberInfo(){
        return this.memberInfo;
    }
    
    public Service getServiceInfo(){
        return this.serviceInfo;
    }
    
    public String getComment(){
       return this.comment;
    }
    
    public int getDatabaseId(){
        return this.databaseId;
    }
    
    //Setters
    public void setProviderInfo(Provider providerInfo){
        this.providerInfo = providerInfo;
    }
    
    public void setMemberInfo(Member memberInfo){
        this.memberInfo = memberInfo;
    }
    
    public void setServiceInfo(Service serviceInfo){
        this.serviceInfo = serviceInfo;
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }
    public void setDatabaseId(int databaseId){
       this.databaseId = databaseId;
    }
    
    //Methods
    public static String convertDateFormat(String date, String current_date_format, String new_date_format) throws ParseException{
        DateFormat current_format = new SimpleDateFormat(current_date_format);
        DateFormat new_format = new SimpleDateFormat(new_date_format);
        Date date_current = current_format.parse(date);
        return new_format.format(date_current);
    }
    
    public int saveToDatabase(){
        int affectedRows = 0;
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("INSERT INTO chocanon_db.visits(provider_id, member_id, service_id, visit_date, comment) VALUES(%s,%s,%s,'%s','%s');", this.providerInfo.getDatabaseId(), this.memberInfo.getDatabaseId(), this.serviceInfo.getDatabaseId(), Visit.convertDateFormat(this.getVisitDate(), "MM-dd-yyyy", "yyyy-MM-dd"), this.comment);
            Log.debug("Visit", "Query: " + strSelect);
            //Execute Query
            affectedRows = stmt.executeUpdate(strSelect);
            if(affectedRows > 0){
                Log.debug("Visit", affectedRows + " rows were affected.");
            }else{
                Log.debug("Visit", "Save To Database Failed..");
            }
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Visit", ex.toString());
        }  
        return affectedRows;
    }
    
    //Static getters
    public static Visit[] getVisitsByCardNumber(int cardNumber, String startDate, String endDate){
        ArrayList<Visit> memberVisits = new ArrayList<>(); 
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("SELECT visit_id, provider_id, chocanon_db.visits.member_id, service_id, visit_date, comment, received_visit_ts FROM chocanon_db.visits JOIN members ON members.member_id = chocanon_db.visits.member_id WHERE card_number = %s AND visit_date BETWEEN CAST('%s' AS DATE) AND CAST('%s' AS DATE)", cardNumber, startDate, endDate);
            Log.debug("Visit", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            //Get Results
            while (rset.next()) {
                //Build the visit
                Visit v = new Visit(
                        rset.getInt("visit_id"),
                        Provider.getProviderByProviderDbId(rset.getInt("provider_id")),
                        Member.getMemberByMemberDbId(rset.getInt("member_id")),
                        Service.getServiceByServiceId(rset.getInt("service_id")),
                        Visit.convertDateFormat(rset.getString("visit_date"), "yyyy-MM-dd", "MM-dd-yyyy"),
                        Visit.convertDateFormat(rset.getString("received_visit_ts"), "yyyy-MM-dd HH:mm:ss", "MM-dd-yyyy HH:mm:ss"),
                        rset.getString("comment")
                );
                
                Log.debug("Visit", "Found Visit: \n" + v.toString());
                //Add Visit
                memberVisits.add(v);
            }
            
            if(memberVisits.size() > 0){
                Log.debug("Visit", "Found " + memberVisits.size() + " Visits");
            }else{
                Log.debug("Visit", "No Visits Found With Card Number: " + cardNumber);
            }
            
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Visit", ex.toString());
        }  
        
        return Arrays.stream(memberVisits.toArray()).toArray(Visit[]::new);
    }
    public static Visit[] getVisitsByProviderNumber(int providerNumber, String startDate, String endDate){
        ArrayList<Visit> memberVisits = new ArrayList<>(); 
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("SELECT visit_id, chocanon_db.visits.provider_id, member_id, service_id, visit_date, comment, received_visit_ts FROM chocanon_db.visits JOIN providers ON providers.provider_id = chocanon_db.visits.provider_id WHERE provider_number = %s AND visit_date BETWEEN CAST('%s' AS DATE) AND CAST('%s' AS DATE)", providerNumber, startDate, endDate);
            Log.debug("Visit", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            //Get Results
            while (rset.next()) {
                //Build the visit
                Visit v = new Visit(
                        rset.getInt("visit_id"),
                        Provider.getProviderByProviderDbId(rset.getInt("provider_id")),
                        Member.getMemberByMemberDbId(rset.getInt("member_id")),
                        Service.getServiceByServiceId(rset.getInt("service_id")),
                        Visit.convertDateFormat(rset.getString("visit_date"), "yyyy-MM-dd", "MM-dd-yyyy"),
                        Visit.convertDateFormat(rset.getString("received_visit_ts"), "yyyy-MM-dd HH:mm:ss", "MM-dd-yyyy HH:mm:ss"),
                        rset.getString("comment")
                );
                Log.debug("Visit", "Found Visit: \n" + v.toString());
                //Add Visit
                memberVisits.add(v);
            }
            
            if(memberVisits.size() > 0){
                Log.debug("Visit", "Found " + memberVisits.size() + " Visits");
            }else{
                Log.debug("Visit", "No Visits Found With Provider Number: " + providerNumber);
            }
            
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Visit", ex.toString());
        }  
        
        return Arrays.stream(memberVisits.toArray()).toArray(Visit[]::new);
    }
    public static Visit[] getVisitsByDate(String startDate, String endDate){
        ArrayList<Visit> memberVisits = new ArrayList<>(); 
        try {
            //Create Database Connection
            DatabaseConnector dbConn = new DatabaseConnector();
            Connection conn = dbConn.getDatabaseConnection();
            Statement stmt = conn.createStatement();
            //Query
            String strSelect = String.format("SELECT visit_id, chocanon_db.visits.provider_id, member_id, service_id, visit_date, comment, received_visit_ts FROM chocanon_db.visits JOIN providers ON providers.provider_id = chocanon_db.visits.provider_id WHERE visit_date BETWEEN CAST('%s' AS DATE) AND CAST('%s' AS DATE)", startDate, endDate);
            Log.debug("Visit", "Query: " + strSelect);
            //Execute Query
            ResultSet rset = stmt.executeQuery(strSelect);
            //Get Results
            while (rset.next()) {
                //Build the visit
                Visit v = new Visit(
                        rset.getInt("visit_id"),
                        Provider.getProviderByProviderDbId(rset.getInt("provider_id")),
                        Member.getMemberByMemberDbId(rset.getInt("member_id")),
                        Service.getServiceByServiceId(rset.getInt("service_id")),
                        Visit.convertDateFormat(rset.getString("visit_date"), "yyyy-MM-dd", "MM-dd-yyyy"),
                        Visit.convertDateFormat(rset.getString("received_visit_ts"), "yyyy-MM-dd HH:mm:ss", "MM-dd-yyyy HH:mm:ss"),
                        rset.getString("comment")
                );
                Log.debug("Visit", "Found Visit: \n" + v.toString());
                //Add Visit
                memberVisits.add(v);
            }
            
            if(memberVisits.size() > 0){
                Log.debug("Visit", "Found " + memberVisits.size() + " Visits");
            }
            
            //Close database
            dbConn.closeDatabaseConnection();
        } catch (Exception ex) {
            Log.error("Visit", ex.toString());
        }  
        
        return Arrays.stream(memberVisits.toArray()).toArray(Visit[]::new);
    }
    
    @Override
    public String toString(){
       String recVDT = "";
       if(this.receivedVisitDateTime != null){
            recVDT = currentDateTimeFormatter.format(this.receivedVisitDateTime);
       }
       return(
       "--------------------------------------------------------------------\n" +
               "Database Id: " + this.getDatabaseId() + "\n" +
         
               "Received By Computer Date: " + recVDT + "\n" +
               "Provider Info: " + this.getProviderInfo().toString() +
               "Member Info: " + this.getMemberInfo().toString() +
               "Service Info: " + this.getServiceInfo().toString() +
               "Visit Date: " + dateOfServiceFormatter.format(this.dateOfService) + "\n" +
               "Comment: " + this.comment +
        "\n--------------------------------------------------------------------\n"
        );
    }
}
