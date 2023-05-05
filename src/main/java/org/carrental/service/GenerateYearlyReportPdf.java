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

public class GenerateYearlyReportPdf {
    public static void GenerateYearlyReportPdf(JTable jTable, String[][] data,int year,int[] profit) throws FileNotFoundException, DocumentException {
        Document document=new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document,new FileOutputStream("YearlyReport.pdf"));
        document.open();

        PdfPTable pdfTable = new PdfPTable(jTable.getColumnCount());
        Paragraph paragraph = new Paragraph("YEARLY REPORT OF " +year+" \n\n\n");
        document.add(paragraph);

        for (int i = 0; i < jTable.getColumnCount(); i++) {
            pdfTable.addCell(new PdfPCell(new Paragraph(jTable.getColumnName(i))));
        }
        for (int i = 0; i < jTable.getRowCount(); i++) {
            for (int j = 0; j < jTable.getColumnCount(); j++) {
                pdfTable.addCell(new PdfPCell(new Paragraph(jTable.getValueAt(i, j).toString())));
            }
        }
        document.add(pdfTable);
        Paragraph commissionPara = new Paragraph();
        commissionPara.add("\nTOTAL SALE FROM THIS VEHICLE : " +profit[1]);
        commissionPara.add("\nTOTAL COMMISSION GIVEN FROM THIS VEHICLE : " +profit[2]);
        commissionPara.add("\nTOTAL PROFIT FROM THIS VEHICLE : " +profit[0]);
        document.add(commissionPara);

        document.close();
        JOptionPane.showMessageDialog(null, "PDF report generated successfully!");

    }
}
