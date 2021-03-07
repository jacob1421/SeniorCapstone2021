/* 
    File: EFTDataReport.java
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
    Description: Holds the EFT Data. The class also generates a PDF.
 */
package chocanon.Models;

import java.math.BigDecimal;

/**
 *
 * @author jakeb
 */
public class EFTDataReport {
    //Data Attributes
    private Visit[] providerVisits = null;
    
    public EFTDataReport(String startDate, String endDate){
        this.providerVisits = Visit.getVisitsByDate(startDate, endDate);
    }
    
    public BigDecimal getTotalFeeForProviderVisits(){
        /* Create a sum variable. Loop providerVists. Get VisitFee and add it to your sum variable. Return sum variable*/
        return null;
    }
    
    /*
                                EFT DATA REPORT
        Provider Number
        Provider Name
        Amount to be transferred
        
        Provider Number
        Provider Name
        Amount to be transferred
    
        Provider Number
        Provider Name
        Amount to be transferred
    
        Filename Format: EFTDataReportMM-DD-YYY -> EFTDataReport03-03-2021.pdf
    */
    public void generateReportPDF(){
        
    }
}
