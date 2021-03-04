/* 
    File: ServicesReport.java
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
    Description: A class that generates a report of all services. The class generates a PDF.
 */
package chocanon.Models;

public class ServicesReport {
    //Data attributes
    private Service[] services;
    
    public ServicesReport(){
        this.services = Service.getAllServices();
    }
    
    /*
        **YOU DONT NEED ANY ADDITIONAL FUNCTIONS - USE THE CLASS FUNCTIONS**
    
                                SERVICES REPORT
        Service Code
        Service Name
        Service Fee
        
        Service Code
        Service Name
        Service Fee
    
        Service Code
        Service Name
        Service Fee
        
        Filename Format: ServicesMM-DD-YYY -> Services03-03-2021.pdf
    */
    
    public void generateReportPDF(){
        
    }
}
