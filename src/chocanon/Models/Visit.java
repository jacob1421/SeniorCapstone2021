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
    Updated: 2/13/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: Models the visit data that is pulled from the database.
 */
package chocanon.Models;
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
    
    //Constructors
    public Visit(){
    
    }
    
    public Visit(Provider providerInfo, Member memberInfo, Service serviceInfo, String dateOfService, String receivedVisitDateTime, String comment) throws ParseException{
        this.providerInfo = providerInfo;
        this.memberInfo = memberInfo;
        this.serviceInfo = serviceInfo;
        this.comment = comment;
        this.dateOfService = dateOfServiceFormatter.parse(dateOfService);
        this.receivedVisitDateTime = currentDateTimeFormatter.parse(receivedVisitDateTime);
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
    
    @Override
    public String toString(){
       return(
       "--------------------------------------------------------------------\n" +
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
