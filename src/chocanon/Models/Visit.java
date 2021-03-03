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
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class Visit {
    //Date Format
    SimpleDateFormat currentDateTimeFormatter = new SimpleDateFormat("MM-dd-YYYY HH:mm:ss");
    SimpleDateFormat dateOfServiceFormatter = new SimpleDateFormat("MM-dd-YYYY");
    
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
    public Date getVisitDate(){
        return this.dateOfService;
    }
    
    public Date getReceivedVisitDateTime(){
        return this.receivedVisitDateTime;
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
    public void setVisitDate(String dateOfService) throws ParseException{
        this.dateOfService = dateOfServiceFormatter.parse(dateOfService);
    }
    
    public void setReceivedVisitDateTime(String receivedVisitDateTime) throws ParseException{
        this.receivedVisitDateTime = currentDateTimeFormatter.parse(receivedVisitDateTime);
    }
    
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
    
    //Static getters
    public static Visit[] getVisitsByCardNumber(int cardNumber, Date startDate, Date endDate){
        Visit memberVisits[] = null;
        //Database call to get all the member visits within the startDate to the endDate
        return memberVisits;
    }
    public static Visit[] getVisitsByProviderNumber(int providerNumber, Date startDate, Date endDate){
        Visit memberVisits[] = null;
        //Database call to get all the provider visits within the startDate to the endDate
        return memberVisits;
    }
    public static Visit[] getVisitsByDate(Date startDate, Date endDate){
        Visit memberVisits[] = null;
        //Database call to get all the visits within the startDate to the endDate
        return memberVisits;
    }
    
    @Override
    public String toString(){
       return(
       "--------------------------------------------------------------------\n" +
               "Database Id: " + this.getDatabaseId() + "\n" +
               "Received By Computer Date: " + this.receivedVisitDateTime.toString() + "\n" +
               "Provider Info: " + this.getProviderInfo().toString() +
               "Member Info: " + this.getMemberInfo().toString() +
               "Service Info: " + this.getServiceInfo().toString() +
               "Visit Date: " + this.dateOfService.toString() + "\n" +
               "Comment: " + this.comment +
        "\n--------------------------------------------------------------------\n"
        );
    }
}
