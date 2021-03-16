/* 
    File: SummaryReport.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 3/3/2021
    Updated by: Lydia Clarke
    Updated: 3/16/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: A class to hold the data for all the provider visits within a start and end date. The class also generates a PDF.
 */
package chocanon.Models;


import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author jakeb
 */
public class SummaryReport {
    //Data Attributes
    private Visit[] providerVisits = null;
    private ArrayList<Integer> processedProviderNumbers = new ArrayList<Integer>();
    private String startDate = null;
    private String endDate = null;
    public SummaryReport(String startDate, String endDate){
        //Get the provider visits between the start and end date
        this.providerVisits = Visit.getVisitsByDate(startDate, endDate);
         this.startDate = startDate;
         this.endDate = endDate;
    }
    
    //Getters
    public int getNumberOfConsultationsByProviderNumber(int providerNumber){
        /* Get the number of per provider consultations by using a loop etc*/
       return 0;
    }
    
    public BigDecimal getTotalFeeByProviderNumber(int providerNumber){   
         return null;
    }
    
    public int getTotalNumberOfProviders(){
        /* Make an array of provider numbers in this function and unique the array. Then get the count of items in array. Return that. */
        return 0;
    }
    
    public int getTotalNumberOfProviderConsultations(){
        return providerVisits.length;
    }
    
    public double getTotalFeeForProviderVisits(){ //changed to double from BigDecimal for now
        double visitsSum = 0.00;
           for(int i = 0; i < providerVisits.length; i++){ 
                visitsSum += providerVisits[i].getServiceInfo().getServiceFee().doubleValue();
           }
        
        return visitsSum;
    }
   
    public Boolean isProviderProcessed(int providerNumber){
      Boolean providerProcessed = false;
      for (int counter = 0; counter < processedProviderNumbers.size(); counter++) { 	
          if(processedProviderNumbers.get(counter) == providerNumber){
              providerProcessed = true;
              break;
          }		
      }   
      return providerProcessed;
    }
    public Visit[] nextProviderVisits(){
      ArrayList<Visit> providerVisits = new ArrayList<>(); 
      int notProcessedProviderId = 0;
      for(int i = 0; i< this.providerVisits.length;i++){
          if(isProviderProcessed(this.providerVisits[i].getProviderInfo().getProviderNumber()) != true && notProcessedProviderId == 0){
                //If the provider has already been processed we skip the iteration
                notProcessedProviderId = this.providerVisits[i].getProviderInfo().getProviderNumber();
            }
          if(notProcessedProviderId != 0){
              if(this.providerVisits[i].getProviderInfo().getProviderNumber() == notProcessedProviderId){
                  providerVisits.add(this.providerVisits[i]);
              }
          }
      }
      if(notProcessedProviderId != 0){
          this.processedProviderNumbers.add(notProcessedProviderId);
           return Arrays.stream(providerVisits.toArray()).toArray(Visit[]::new);
      }else
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
       System.out.println("Generating Summary Report");
      
       
         File pdfFile = new File(String.format("%s\\Generated_Reports\\SummaryReports\\SummaryReport%s.pdf", System.getProperty("user.dir"),LocalDate.now()));
         //Attempts to create a directory. If its already made, nothing happens. Otherwise, it will create the directory
        pdfFile.getParentFile().mkdirs();

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfFile.getAbsolutePath()));
        Document doc = new Document(pdfDoc);
        
        Paragraph header = new Paragraph("Summary Report")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(24)
                .setFontColor(ColorConstants.BLACK)
                .setBold();
        header.setTextAlignment(TextAlignment.CENTER);
        doc.add(header);
        
        Paragraph fromAndTo = new Paragraph("From: " + this.startDate + "          To: " + this.endDate)
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(12)
                .setFontColor(ColorConstants.BLACK);
        fromAndTo.setTextAlignment(TextAlignment.CENTER);
        doc.add(fromAndTo);
      
       Visit[] pVisits = nextProviderVisits();
       int i;
       int numberOfProviders = 0;
       int totalConsults = 0;
       int consults = 0;
       double totals = 0;
       double feeTotals = 0;
       while(pVisits != null){
        for(i = 0;i < pVisits.length;i++){
             totalConsults++;
             consults++;
             feeTotals += pVisits[i].getServiceInfo().getServiceFee().doubleValue();
           }
           totals = getTotalFeeForProviderVisits();
           
           numberOfProviders++;
        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

        Cell providerNameCell = new Cell().add(new Paragraph("Provider Name"));
        providerNameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(providerNameCell);
        Cell providerName = new Cell().add(new Paragraph(pVisits[0].getProviderInfo().getFirstName() + " " + pVisits[0].getProviderInfo().getLastName()));
        table.addCell(providerName);
        
        Cell providerNumberCell = new Cell().add(new Paragraph("Provider Number"));
        providerNumberCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(providerNumberCell);
         Cell providerNumberDataCell = new Cell().add(new Paragraph(String.valueOf(pVisits[0].getProviderInfo().getProviderNumber())));
        table.addCell(providerNumberDataCell);
        
        Cell consultationCell = new Cell().add(new Paragraph("Number of Consultations"));
        consultationCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(consultationCell);
        Cell consultation= new Cell().add(new Paragraph(""+consults));
        table.addCell(consultation);
        
        Cell totalFeeCell = new Cell().add(new Paragraph("Total Fee"));
        totalFeeCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(totalFeeCell);
        Cell totalFee = new Cell().add(new Paragraph("$"+String.format("%.2f",feeTotals)));
        table.addCell(totalFee);
        doc.add(table);
        Paragraph space = new Paragraph(" ");
                doc.add(space);
        consults = 0;
        feeTotals = 0;
           pVisits = nextProviderVisits();
       }
       /*Number of providers provided services
        Total number of consultations
        Total provider fees*/
       
        Table table2 = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        Cell numProvidersCell = new Cell().add(new Paragraph("Total Number of Providers provided Services: " + numberOfProviders));
        table2.addCell(numProvidersCell);
        Cell totalCell = new Cell().add(new Paragraph("Total Number of Consultations: " +totalConsults));
        table2.addCell(totalCell);
        Cell totalFeesCell = new Cell().add(new Paragraph("Total provider fees: $" + String.format("%.2f",totals)));
        table2.addCell(totalFeesCell);
        doc.add(table2);
            doc.close();
       }
       
      
    
}
