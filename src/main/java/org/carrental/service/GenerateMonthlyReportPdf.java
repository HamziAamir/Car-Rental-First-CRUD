package org.carrental.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.carrental.domain.Booking;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.List;

public class GenerateMonthlyReportPdf {

    public static void generatePdf(JTable jTable , List<Booking> commissionAndAmount, Date startDate, Date endDate) throws FileNotFoundException, DocumentException {

        Document document=new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document,new FileOutputStream("Monthlyreport.pdf"));
        document.open();

        PdfPTable pdfTable = new PdfPTable(jTable.getColumnCount());
        pdfTable.setTotalWidth(2000);
        Paragraph paragraph = new Paragraph("MONTHLY REPORT\nStart Date : " + startDate+"  End Date : "+endDate +"\n\n");
        document.add(paragraph);


        for (int i = 0; i < jTable.getColumnCount(); i++) {
            pdfTable.addCell(new PdfPCell(new Paragraph(jTable.getColumnName(i))));
        }

        // Add the data rows to the PDF table
        for (int i = 0; i < jTable.getRowCount(); i++) {
            for (int j = 0; j < jTable.getColumnCount(); j++) {
                pdfTable.addCell(new PdfPCell(new Paragraph(jTable.getValueAt(i, j).toString())));
            }
        }

        document.add(pdfTable);

        Integer commission = commissionAndAmount.get(0).getCommission();
        Integer amount = commissionAndAmount.get(0).getTotalAmount();
        Integer profit = amount-commission;
        Paragraph commissionPara = new Paragraph();
        commissionPara.add("TOTAL AMOUNT : " +amount+"\n\n");
        commissionPara.add("COMMISSION TO OWNER : " +commission+"\n\n");
        commissionPara.add("PROFIT : " +profit);
        document.add(commissionPara);


        document.close();
        JOptionPane.showMessageDialog(null, "PDF report generated successfully!");

    }
}
