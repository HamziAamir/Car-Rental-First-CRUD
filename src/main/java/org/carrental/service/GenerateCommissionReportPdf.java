package org.carrental.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GenerateCommissionReportPdf {


    public static void generatePdf(JTable jTable , String startDate , String endDate) throws IOException, DocumentException {

        Document document=new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, Files.newOutputStream(Paths.get("CommissionReport.pdf")));
        document.open();

        PdfPTable pdfTable = new PdfPTable(jTable.getColumnCount());
        Paragraph paragraph = new Paragraph("COMMISSION REPORT :\n\n\n");
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
