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

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
/**
 *
 * @author jakeb
 */
public class SummaryReport {
    //Data Attributes
    private Visit[] providerVisits = null;
    
    public SummaryReport(Visit[] providerVisits){
        this.providerVisits = providerVisits;
    }
    
    //Getters
    public int getNumberOfConsultationsByProviderNumber(int providerNumber){
        /* Get the number of per provider consultations by using a loop etc*/
        int numConsultations = 0;
        for(int i =0;i<this.providerVisits.length;i++){
            if(this.providerVisits[0].getProviderInfo().getProviderNumber() == providerNumber){
                numConsultations +=1;
            }
        }
        return numConsultations;
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
    public void generateReportPDF() throws FileNotFoundException, IOException{
PdfWriter writer = new PdfWriter("SUMMMARYREPORT.PDF");
PdfDocument pdf = new PdfDocument(writer);
Document document = new Document(pdf);
// Create a PdfFont
// Add a Paragraph
document.add(new Paragraph("iText is:"));
// Create a List
List list = new List()
    .setSymbolIndent(12)
    .setListSymbol("\u2022");
// Add ListItem objects
list.add(new ListItem("Never gonna give you up"))
    .add(new ListItem("Never gonna let you down"))
    .add(new ListItem("Never gonna run around and desert you"))
    .add(new ListItem("Never gonna make you cry"))
    .add(new ListItem("Never gonna say goodbye"))
    .add(new ListItem("Never gonna tell a lie and hurt you"));
// Add the list
document.add(list);
document.close();

    }
}
