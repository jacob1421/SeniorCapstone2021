/* 
    File: ProviderReport.java
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
    Description: A class to hold the data for a single provider. The class also generates a PDF.
 */
package chocanon.Models;

/**
 *
 * @author jakeb
 */
public class ProviderReport {
        //Data Attributes
    private Provider provider = null;
    private Visit[] providerVisits = null;
    
    public ProviderReport(String startDate, String endDate){
        //Get the provider visits between the start and end date
        this.providerVisits = Visit.getVisitsByDate(startDate, endDate);
    }
    
    /*
        **YOU DONT NEED ANY ADDITIONAL FUNCTIONS - USE THE CLASS FUNCTIONS**
        Report should look something like (PAGE 629)
    
                                PROVIDER REPORT
        Provider name
        Provider number
        Provider street address
        Provider city
        Provider state
        Provider ZIP code
            Date of Service(MM-DD-YYYY) (dateOfService)
            Date Recieved by Computer (receivedVisitDateTime)
            Member name
            Member number
            Service code
            Fee to be paid
            
            Date of Service(MM-DD-YYYY) (dateOfService)
            Date Recieved by Computer (receivedVisitDateTime)
            Member name
            Member number
            Service code
            Fee to be paid
    
            Date of Service(MM-DD-YYYY) (dateOfService)
            Date Recieved by Computer (receivedVisitDateTime)
            Member name
            Member number
            Service code
            Fee to be paid ($999.99)
        
        Total number of consulations with members
        Total fee for the week ($99,999.99)
    
        Filename Format: ProviderNameMM-DD-YYY -> JohnDoe03-03-2021.pdf
    */
    public void generateReportPDF(){

    }
}
