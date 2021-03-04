/* 
    File: MemberReport.java
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
    Description: A class to hold the data for a single user. The class also generates a PDF.
 */
package chocanon.Models;

import java.util.Date;


public class MemberReport {
    //Data Attributes
    private Member mem = null;
    private Visit[] memberVisits = null;
    
    public MemberReport(Member mem, String startDate, String endDate){
        //Set the member for the report
        this.mem = mem;
        //Get the members visits between the start and end date
        this.memberVisits = Visit.getVisitsByCardNumber(this.mem.getCardNumber(), startDate, endDate);
    }
    
    /*
        **YOU DONT NEED ANY ADDITIONAL FUNCTIONS - USE THE CLASS FUNCTIONS**
        Report should look something like (PAGE 628)
    
                                MEMBER REPORT
        Member name
        Member number
        Member street address
        Member city
        Member state
        Member ZIP code
            Date of Service(MM-DD-YYYY)
            Provider name
            Service name
            
            Date of Service(MM-DD-YYYY)
            Provider name
            Service name
            
            Date of Service(MM-DD-YYYY)
            Provider name
            Service name
        
        Filename Format: MemberNameMM-DD-YYY -> JohnDoe03-03-2021.pdf
    */
    public void generateReportPDF(){
        
    }
}
