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
import java.time.LocalDate;

public class ServicesReport {
    //Data attributes
    final private Service[] services;
    
    public ServicesReport(){
        this.services = Service.getAllServices();
    }
    
    /*
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
        
        Filename Format: ServicesYYYY-MM-DD -> Services2021-03-08.pdf
    */
    
    public void generateReportPDF() throws FileNotFoundException, IOException{
        File pdfFile = new File(String.format("%s\\Generated_Reports\\ServiceReports\\Services%s.pdf", System.getProperty("user.dir"), LocalDate.now()));
         //Attempts to create a directory. If its already made, nothing happens. Otherwise, it will create the directory
        pdfFile.getParentFile().mkdirs();

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfFile.getAbsolutePath()));
        Document doc = new Document(pdfDoc);
        
        Paragraph header = new Paragraph("Services Report")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(24)
                .setFontColor(ColorConstants.BLACK)
                .setBold();
        header.setTextAlignment(TextAlignment.CENTER);
        doc.add(header);
        
        Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();

        Cell serviceCodeNameCell = new Cell().add(new Paragraph("Service Code"));
        serviceCodeNameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(serviceCodeNameCell);
        
        Cell serviceNameCell = new Cell().add(new Paragraph("Service Name"));
        serviceNameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(serviceNameCell);
        
        Cell serviceFeeCell = new Cell().add(new Paragraph("Service Fee"));
        serviceFeeCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(serviceFeeCell);
        
        for(int i =0;i<this.services.length;i++){
            Cell serviceCode = new Cell().add(new Paragraph(String.valueOf(this.services[i].getServiceCode())));
            table.addCell(serviceCode);
            
            Cell serviceName = new Cell().add(new Paragraph(this.services[i].getServiceName()));
            table.addCell(serviceName);
            
            Cell serviceFee = new Cell().add(new Paragraph("$" + String.format("%5.2f", this.services[i].getServiceFee().doubleValue())));
            table.addCell(serviceFee);
        }

        doc.add(table);

        doc.close();
    }
}
