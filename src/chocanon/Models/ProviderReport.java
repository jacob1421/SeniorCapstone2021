/* 
    File: ProviderReport.java
    Project: COSC-4360 Capstone Project Team #0
    University: McMurry University
    Course: COSC–4360 Spring 2021
    Instructor: Mr. Brozovic
    Programmer: Jacob Bremiller
    Created by: Jacob Bremiller
    Created: 3/3/2021
    Updated by: Lydia Clarke
    Updated: 3/15/2021
    Compiler: Apache NetBeans IDE for Java SE
    Description: A class to hold the data for a single provider. The class also generates a PDF.
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
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jakeb
 */
public class ProviderReport {

    //Data Attributes
    private Visit[] providerVisits = null;
    private ArrayList<Integer> processedProviderNumbers = new ArrayList<Integer>();
    private String startDate = null;
    private String endDate = null;

    public ProviderReport(String startDate, String endDate) {
        //Get the provider visits between the start and end date
        this.providerVisits = Visit.getVisitsByDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Boolean isProviderProcessed(int providerNumber) {
        Boolean providerProcessed = false;
        for (int counter = 0; counter < processedProviderNumbers.size(); counter++) {
            if (processedProviderNumbers.get(counter) == providerNumber) {
                providerProcessed = true;
                break;
            }
        }
        return providerProcessed;
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

    public Visit[] nextProviderVisits() {
        ArrayList<Visit> providerVisits = new ArrayList<>();
        int notProcessedProviderId = 0;
        for (int i = 0; i < this.providerVisits.length; i++) {
            if (isProviderProcessed(this.providerVisits[i].getProviderInfo().getProviderNumber()) != true && notProcessedProviderId == 0) {
                //If the provider has already been processed we skip the iteration
                notProcessedProviderId = this.providerVisits[i].getProviderInfo().getProviderNumber();
            }
            if (notProcessedProviderId != 0) {
                if (this.providerVisits[i].getProviderInfo().getProviderNumber() == notProcessedProviderId) {
                    providerVisits.add(this.providerVisits[i]);
                }
            }
        }
        if (notProcessedProviderId != 0) {
            this.processedProviderNumbers.add(notProcessedProviderId);
            return Arrays.stream(providerVisits.toArray()).toArray(Visit[]::new);
        } else {
            return null;
        }
    }

    public Table visitInfoTable(Visit v) {
        /* Date of Service(MM-DD-YYYY) (dateOfService)
            Date Recieved by Computer (receivedVisitDateTime)
            Member name
            Member number
            Service code
            Fee to be paid*/

        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();

        Cell DOSCell = new Cell().add(new Paragraph("Date of Service"));
        DOSCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(DOSCell);
        Cell dos = new Cell().add(new Paragraph(v.getVisitDate().toString()));
        table.addCell(dos);

        Cell DORCell = new Cell().add(new Paragraph("Date Recieved By Computer"));
        DORCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(DORCell);
        Cell dor = new Cell().add(new Paragraph(v.getReceivedVisitDateTime().toString()));
        table.addCell(dor);

        Cell memberNameCell = new Cell().add(new Paragraph("Member Name"));
        memberNameCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(memberNameCell);
        Cell memberName = new Cell().add(new Paragraph(v.getMemberInfo().getFirstName() + " " + v.getMemberInfo().getLastName()));
        table.addCell(memberName);

        Cell memberNumberCell = new Cell().add(new Paragraph("Member Number"));
        memberNumberCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(memberNumberCell);
        Cell memberNumber = new Cell().add(new Paragraph(String.valueOf(v.getMemberInfo().getCardNumber())));
        table.addCell(memberNumber);

        Cell serviceCodeCell = new Cell().add(new Paragraph("Service Code"));
        serviceCodeCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(serviceCodeCell);
        Cell serviceCode = new Cell().add(new Paragraph(String.valueOf(v.getServiceInfo().getServiceCode())));
        table.addCell(serviceCode);

        Cell serviceFeeCell = new Cell().add(new Paragraph("Service Fee"));
        serviceFeeCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
        table.addCell(serviceFeeCell);
        Cell ServiceFee = new Cell().add(new Paragraph("$" + String.format("%5.2f", v.getServiceInfo().getServiceFee())));
        table.addCell(ServiceFee);

        table.setMargins(10, 0, 0, 0);

        return table;
    }

    public void generateReportPDF() throws IOException {

        System.out.println("Generating Provider Report");
        Visit[] pVisits = nextProviderVisits();
        while (pVisits != null) {
            File pdfFile = new File(String.format("%s\\Generated_Reports\\ProviderReports\\%s%s.pdf", System.getProperty("user.dir"), pVisits[0].getProviderInfo().getFirstName() + pVisits[0].getProviderInfo().getLastName(), LocalDate.now()));
            //Attempts to create a directory. If its already made, nothing happens. Otherwise, it will create the directory
            pdfFile.getParentFile().mkdirs();

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(pdfFile.getAbsolutePath()));
            Document doc = new Document(pdfDoc);

            Paragraph header = new Paragraph("Provider Report")
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
            /*Provider name
        Provider number
        Provider street address
        Provider city
        Provider state
        Provider ZIP code*/
 /* provider heading */
            Paragraph title = new Paragraph("Provider Information")
                    .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                    .setFontSize(12)
                    .setFontColor(ColorConstants.BLACK)
                    .setBold();
            title.setTextAlignment(TextAlignment.CENTER);
            doc.add(title);

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

            Cell providerStreetCell = new Cell().add(new Paragraph("Provider Street Address"));
            providerStreetCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            table.addCell(providerStreetCell);
            Cell providerStreet = new Cell().add(new Paragraph(String.valueOf(pVisits[0].getProviderInfo().getStreetAddress())));
            table.addCell(providerStreet);

            Cell providerCityCell = new Cell().add(new Paragraph("Provider City"));
            providerCityCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            table.addCell(providerCityCell);
            Cell providerCity = new Cell().add(new Paragraph(String.valueOf(pVisits[0].getProviderInfo().getCity())));
            table.addCell(providerCity);

            Cell providerStateCell = new Cell().add(new Paragraph("Provider State"));
            providerStateCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            table.addCell(providerStateCell);
            Cell providerState = new Cell().add(new Paragraph(String.valueOf(pVisits[0].getProviderInfo().getState())));
            table.addCell(providerState);

            Cell providerZipCell = new Cell().add(new Paragraph("Provider ZIP Code"));
            providerZipCell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            table.addCell(providerZipCell);
            Cell providerZip = new Cell().add(new Paragraph(String.valueOf(pVisits[0].getProviderInfo().getZipCode())));
            table.addCell(providerZip);

            doc.add(table);

            /*member visit*/
            Paragraph title2 = new Paragraph("Consultations")
                    .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                    .setFontSize(12)
                    .setFontColor(ColorConstants.BLACK)
                    .setBold();
            title2.setTextAlignment(TextAlignment.CENTER);
            title2.setMarginBottom(-10);
            doc.add(title2);

            int i;
            double visitsSum = 0.00;
            for (i = 0; i < pVisits.length; i++) {
                visitsSum += pVisits[i].getServiceInfo().getServiceFee().doubleValue();
                Paragraph space = new Paragraph(" ");
                doc.add(space);
                doc.add(visitInfoTable(pVisits[i]));
            }

            //number of consultations total fee 
            Paragraph total = new Paragraph("Weekly Totals")
                    .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                    .setFontSize(12)
                    .setFontColor(ColorConstants.BLACK)
                    .setBold();
            total.setTextAlignment(TextAlignment.CENTER);
            doc.add(total);

            Table table2 = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
            Cell consultationCell = new Cell().add(new Paragraph("Total Number of Consultations With Members: " + i));
            table2.addCell(consultationCell);
            Cell totalCell = new Cell().add(new Paragraph("Total Fee for the week: $" + String.format("%.2f", visitsSum)));
            table2.addCell(totalCell);
            doc.add(table2);
            doc.close();
            pVisits = nextProviderVisits();
        }

    }
}
