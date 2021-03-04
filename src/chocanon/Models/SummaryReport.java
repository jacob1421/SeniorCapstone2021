/* 
    File: SummaryReport.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 3/3/2021
    Updated by: Jacob Bremiller
    Updated: 3/3/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: A class to hold the data for all the provider visits within a start and end date. The class also generates a PDF.
 */
package chocanon.Models;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jakeb
 */
public class SummaryReport {
    //Data Attributes
    private Visit[] providerVisits = null;
    
    public SummaryReport(String startDate, String endDate){
        this.providerVisits = Visit.getVisitsByDate(startDate, endDate);
    }
    
    //Getters
    public int getNumberOfConsultationsByProviderNumber(int providerNumber){
        /* Get the number of per provider consultations by using a loop etc*/
        return 0;
    }
    
    public BigDecimal getTotalFeeByProviderNumber(int providerNumber){
        /* Get the total providers fees by using a loop etc*/
        return null;
    }
    
    public int getTotalNumberOfProviders(){
        /* Make an array of provider numbers in this function and unique the array. Then get the count of items in array. Return that. */
        return 0;
    }
    
    public int getTotalNumberOfProviderConsultations(){
        return providerVisits.length;
    }
    
    public BigDecimal getTotalFeeForProviderVisits(){
        /* Create a sum variable. Loop providerVists. Get VisitFee and add it to your sum variable. Return sum variable*/
        return null;
    }
    
    
    /*
        Report should look something like (PAGE 628)
    
                                SUMMARY REPORT
        Provider Number
        Provider Name
        Number of consultations
        Total fee
    
        Provider Number
        Provider Name
        Number of consultations
        Total fee
    
        Provider Number
        Provider Name
        Number of consultations
        Total fee
        
        Number of providers provided services
        Total number of consultations
        Total provider fees
    
        Filename Format: SummaryReportMM-DD-YYY -> SummaryReport03-03-2021.pdf
    */
    public void generateReportPDF(){
        
    }
}
