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
import java.util.Arrays;


public class MemberReport {
    //Data Attributes
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
    
        public Visit[] nextMemberVisits(){
      ArrayList<Visit> memberVisits = new ArrayList<>(); 
      int notProcessedMemberId = 0;
      for(int i = 0; i< this.memberVisits.length;i++){
          if(isMemberProcessed(this.memberVisits[i].getMemberInfo().getDatabaseId()) != true && notProcessedMemberId == 0){
                //If the provider has already been processed we skip the iteration
                notProcessedMemberId = this.memberVisits[i].getMemberInfo().getDatabaseId();
            }
          if(notProcessedMemberId != 0){
              if(this.memberVisits[i].getMemberInfo().getDatabaseId() == notProcessedMemberId){
                  memberVisits.add(this.memberVisits[i]);
              }
          }
      }
      if(notProcessedMemberId != 0){
          this.processedMemberNumbers.add(notProcessedMemberId);
           return Arrays.stream(memberVisits.toArray()).toArray(Visit[]::new);
      }else
           return null;
    }
    
    public Table memberInfoTable(Visit b){
      /*  Member name
        Member number
        Member street address
        Member city
        Member state
        Member ZIP code */
        Table table2 = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
         
        Cell memberNameCell = new Cell().add(new Paragraph("Member Name"));
        memberNameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table2.addCell(memberNameCell);
        Cell memberName = new Cell().add(new Paragraph(b.getMemberInfo().getFirstName() + " " + b.getMemberInfo().getLastName()));
        table2.addCell(memberName);
        
       Cell memberNumberCell = new Cell().add(new Paragraph("Member Number"));
        memberNumberCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table2.addCell(memberNumberCell);
        Cell memberNumber = new Cell().add(new Paragraph(String.valueOf(b.getMemberInfo().getCardNumber())));        
        table2.addCell(memberNumber);        

       Cell memberAddrCell = new Cell().add(new Paragraph("Member street address"));
        memberAddrCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table2.addCell(memberAddrCell);
        Cell memberAddr = new Cell().add(new Paragraph(b.getMemberInfo().getStreetAddress()));
        table2.addCell(memberAddr);    

       Cell memberCityCell = new Cell().add(new Paragraph("Member city"));
        memberCityCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table2.addCell(memberCityCell);
        Cell memberCity = new Cell().add(new Paragraph(b.getMemberInfo().getCity()));
        table2.addCell(memberCity);
        
       Cell memberStateCell = new Cell().add(new Paragraph("Member state"));
        memberStateCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table2.addCell(memberStateCell);
        Cell memberState = new Cell().add(new Paragraph(b.getMemberInfo().getState()));
        table2.addCell(memberState); 

       Cell memberZipCell = new Cell().add(new Paragraph("Member zip code"));
        memberZipCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table2.addCell(memberZipCell);
        Cell memberZip = new Cell().add(new Paragraph(String.valueOf(b.getMemberInfo().getZipCode())));
        table2.addCell(memberZip);            
        
        return table2;
    }    
        
    public Table memberServiceTable(Visit v){
        /* Date of Service(MM-DD-YYYY) (dateOfService)
           Provider name
           Service name
        */
        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
         
        Cell DOSCell = new Cell().add(new Paragraph("Date of Service"));
        DOSCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(DOSCell);
        Cell dos = new Cell().add(new Paragraph(v.getVisitDate()));
        table.addCell(dos);
       
        Cell providerNameCell = new Cell().add(new Paragraph("Provider Name"));
        providerNameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(providerNameCell);
        Cell providerName = new Cell().add(new Paragraph(v.getProviderInfo().getFirstName() + " " + v.getProviderInfo().getLastName()));
        table.addCell(providerName);
        
        Cell serviceNameCell = new Cell().add(new Paragraph("Service Name"));
        serviceNameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(serviceNameCell);
        Cell serviceName = new Cell().add(new Paragraph(String.valueOf(v.getServiceInfo().getServiceName())));
        table.addCell(serviceName);
       
        return table;
    }
            
    
    public void generateReportPDF()throws IOException{
       System.out.println("Generating Member Report");
        Visit[] mVisits = nextMemberVisits();
       while(mVisits != null){
           File pdfFile = new File(String.format("%s\\Generated_Reports\\MemberReports\\%s%s.pdf", System.getProperty("user.dir"),mVisits[0].getMemberInfo().getFirstName() + mVisits[0].getMemberInfo().getLastName(),LocalDate.now()));
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
           /* member header */
       Paragraph memberheader = new Paragraph("Member Information")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(12)
                .setFontColor(ColorConstants.BLACK);
        memberheader.setTextAlignment(TextAlignment.CENTER);
        doc.add(memberheader);
        
           /* member information */
           for(int i = 0; i < mVisits.length; i++){               
                doc.add(memberInfoTable(mVisits[i]));
           }

       Paragraph infoheader = new Paragraph("Service Information")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(12)
                .setFontColor(ColorConstants.BLACK);
        infoheader.setTextAlignment(TextAlignment.CENTER);
        doc.add(infoheader);           
           
           /*member visit*/
           for(int i = 0; i < mVisits.length; i++){    
                doc.add(memberServiceTable(mVisits[i]));
           }
           
           doc.close();
           System.out.println("mVisit length"+mVisits.length);
           System.out.println("Member Number" + mVisits[0].getMemberInfo().getDatabaseId());
           mVisits = nextMemberVisits();
       }    
    }
}

