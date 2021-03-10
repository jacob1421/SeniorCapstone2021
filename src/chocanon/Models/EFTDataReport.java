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
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jakeb
 */
public class EFTDataReport {
    //Data Attributes
    private Visit[] providerVisits = null;
    private ArrayList<Integer> processedProviderNumbers = new ArrayList<Integer>();
    private String startDate = null;
    private String endDate = null;
    
    public EFTDataReport(String startDate, String endDate){
        this.providerVisits = Visit.getVisitsByDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public Double getTotalFeeForProviderVisits(int providerNumber){
        //Add to our processed providers arraylist
        processedProviderNumbers.add(providerNumber);
        /* Create a sum variable. Loop providerVists. Get VisitFee and add it to your sum variable. Return sum variable*/
        double visitsSum = 0.00;
        for(int i = 0;i < providerVisits.length;i++){
            if(providerVisits[i].getProviderInfo().getProviderNumber() == providerNumber){
                   visitsSum += providerVisits[i].getServiceInfo().getServiceFee().doubleValue();
            }
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
    
        Filename Format: EFTDataReportYYYY-MM-DD -> EFTDataReport2021-03-08.pdf
    */
    public void generateReportPDF() throws IOException{
        File pdfFile = new File(String.format("%s\\Generated_Reports\\EFTDataReports\\EFTDataReport%s.pdf", System.getProperty("user.dir"), LocalDate.now()));
         //Attempts to create a directory. If its already made, nothing happens. Otherwise, it will create the directory
        pdfFile.getParentFile().mkdirs();

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfFile.getAbsolutePath()));
        Document doc = new Document(pdfDoc);
        
        Paragraph header = new Paragraph("EFT Data Report")
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
        
        Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();

        Cell providerNumberCell = new Cell().add(new Paragraph("Provider Number"));
        providerNumberCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(providerNumberCell);
        
        Cell providerNameCell = new Cell().add(new Paragraph("Provider Name"));
        providerNameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(providerNameCell);
        
        Cell amountTransferredCell = new Cell().add(new Paragraph("Amount to be transferred"));
        amountTransferredCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(amountTransferredCell);
        
        for(int i = 0;i < providerVisits.length;i++){
            int providerNumber = providerVisits[i].getProviderInfo().getProviderNumber();
            
            if(isProviderProcessed(providerNumber)){
                //If the provider has already been processed we skip the iteration
                continue;
            }
            
            Cell providerNumberDataCell = new Cell().add(new Paragraph(String.valueOf(providerNumber)));
            table.addCell(providerNumberDataCell);

            Cell providerName = new Cell().add(new Paragraph(providerVisits[i].getProviderInfo().getFirstName() + " " + providerVisits[i].getProviderInfo().getLastName()));
            table.addCell(providerName);

            Cell providerTotalVisitsFee = new Cell().add(new Paragraph("$" + String.format("%5.2f", this.getTotalFeeForProviderVisits(providerNumber))));
            table.addCell(providerTotalVisitsFee);
        }

        doc.add(table);

        doc.close();
    }
}
