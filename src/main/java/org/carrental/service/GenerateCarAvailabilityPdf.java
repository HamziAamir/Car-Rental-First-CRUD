package org.carrental.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GenerateCarAvailabilityPdf {


    public static void generatePdf(JTable jTable) throws FileNotFoundException, DocumentException {

        Document document=new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document,new FileOutputStream("CarAvailabilityReport.pdf"));
        document.open();

        PdfPTable pdfTable = new PdfPTable(jTable.getColumnCount());
        Paragraph paragraph = new Paragraph("CAR AVAILABILITY REPORT\n");
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
