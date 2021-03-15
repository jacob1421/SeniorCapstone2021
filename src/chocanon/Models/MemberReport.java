/* 
    File: MemberReport.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 3/3/2021
    Updated by: Stephen Dunn
    Updated: 3/15/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: A class to hold the data for a single user. The class also generates a PDF.
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


public class MemberReport {
    //Data Attributes
    private Member mem = null;
    private Visit[] memberVisits = null;
    private String startDate = null;
    private String endDate = null;
    private ArrayList<Integer> processedMemberNumbers = new ArrayList<Integer>();

            //Get the members visits between the start and end date
    public MemberReport(String startDate, String endDate){
        this.memberVisits = Visit.getVisitsByDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public Boolean isMemberProcessed(int memberNumber){
      Boolean memberProcessed = false;
      for (int counter = 0; counter < processedMemberNumbers.size(); counter++) { 	
          if(processedMemberNumbers.get(counter) == memberNumber){
              memberProcessed = true;
              break;
          }		
      }   
      return memberProcessed;
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
    public void generateReportPDF()throws IOException{
         File pdfFile = new File(String.format("%s\\Generated_Reports\\MemberReports\\MemberReport%s.pdf", System.getProperty("user.dir"), LocalDate.now()));
         //Attempts to create a directory. If its already made, nothing happens. Otherwise, it will create the directory
        pdfFile.getParentFile().mkdirs();

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfFile.getAbsolutePath()));
        Document doc = new Document(pdfDoc);
        
        Paragraph header = new Paragraph("Member Report")
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

        Cell memberNameCell = new Cell().add(new Paragraph("Member Name"));
        memberNameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(memberNameCell);
        
        Cell memberNumberCell = new Cell().add(new Paragraph("Member Number"));
        memberNumberCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(memberNumberCell);
        
        for(int i = 0;i < memberVisits.length;i++){
            int memberNumber = memberVisits[i].getMemberInfo().getDatabaseId();
            
            if(isMemberProcessed(memberNumber)){
                //If the member has already been processed we skip the iteration
                continue;
            }
            
            Cell memberNumberDataCell = new Cell().add(new Paragraph(String.valueOf(memberNumber)));
            table.addCell(memberNumberDataCell);

            Cell memberName = new Cell().add(new Paragraph(memberVisits[i].getMemberInfo().getFirstName() + " " + memberVisits[i].getMemberInfo().getLastName()));
            table.addCell(memberName);
        }

        doc.add(table);

        doc.close();
    }  
    }
        
