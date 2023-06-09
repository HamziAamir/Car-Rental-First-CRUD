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


public class GenerateCarAnalyticsPdf {
    public static void generatePdf(JTable jTable , Date startDate , Date endDate) throws FileNotFoundException, DocumentException {

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, new FileOutputStream("AnalyticsReport.pdf"));
        document.open();

        PdfPTable pdfTable = new PdfPTable(jTable.getColumnCount());
        Paragraph paragraph = new Paragraph("ANALYTICS REPORT\n");
        paragraph.add("START DATE : "+startDate+"\n");
        paragraph.add("END DATE : "+endDate+"\n\n\n");
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

        document.close();
        JOptionPane.showMessageDialog(null, "PDF report generated successfully!");

    }
}
