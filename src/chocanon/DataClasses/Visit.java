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
package chocanon.DataClasses;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;

public class Visit {
    //Date Format
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    //Data Attributes
    private Date visitTs = null;
    private Provider providerInfo = null;
    private Member memberInfo = null;
    private Service serviceInfo = null;
    private String comment = "";
    
    //Constructor
    public Visit(Provider providerInfo, Member memberInfo, Service serviceInfo, String visitTs, String comment) throws ParseException{
        this.providerInfo = providerInfo;
        this.memberInfo = memberInfo;
        this.serviceInfo = serviceInfo;
        this.comment = comment;
        this.visitTs = formatter.parse(visitTs);
    }
    
    //Getters
    public Date getVisitDate(){
        return this.visitTs;
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
    
    @Override
    public String toString(){
       return(
       "--------------------------------------------------------------------\n" +
               "Provider Info: " + this.getProviderInfo().toString() +
               "Member Info: " + this.getMemberInfo().toString() +
               "Service Info: " + this.getServiceInfo().toString() +
               "Visit Date: " + this.visitTs.toString() + "\n" +
               "Comment: " + this.comment +
        "\n--------------------------------------------------------------------\n"
        );
    }
}
