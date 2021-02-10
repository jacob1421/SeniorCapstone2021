/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chocanon.DataClasses;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;
/**
 *
 * @author jakeb
 */
public class Visit {
    //Date Format
    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");  
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
}
