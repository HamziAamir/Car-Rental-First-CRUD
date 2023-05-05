package org.carrental.UI;

import com.itextpdf.text.DocumentException;
import org.carrental.domain.Booking;
import org.carrental.service.BookingService;
import org.carrental.service.DateLabelFormatter;
import org.carrental.service.GenerateMonthlyReportPdf;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

public class MonthlyReportUi {
    BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        new MonthlyReportUi();
    }
    public MonthlyReportUi(){

        JFrame frame = new JFrame("Monthly Report");


        UtilDateModel startModel = new UtilDateModel();
        startModel.setSelected(true);
        UtilDateModel endModel = new UtilDateModel();
        endModel.setSelected(true);

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl startDatePanel = new JDatePanelImpl(startModel,properties);
        JDatePickerImpl startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
        JDatePanelImpl endDatePanel = new JDatePanelImpl(endModel,properties);
        JDatePickerImpl endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
        JButton generatePdfBtn = new JButton("Generate PDF");
        frame.add(startDatePicker);
        frame.add(endDatePicker);
        frame.add(generatePdfBtn);



        generatePdfBtn.addActionListener(e->{

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date startDate = null;
                java.util.Date endDate;
                try {
                    startDate = dateFormat.parse(startDatePicker.getJFormattedTextField().getText());
                    endDate = dateFormat.parse(endDatePicker.getJFormattedTextField().getText());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                java.sql.Date startSqlDate = new java.sql.Date(startDate.getTime());
                java.sql.Date endSqlDate = new java.sql.Date(endDate.getTime());
                String[][] data = bookingService.getByDateRange(startSqlDate, endSqlDate);
                String[] column = {"ID", "CUSTOMER", "VEHICLE", "BOOKING DATE", "COMPLETE DATE", "AMOUNT", "STATUS"};
                JTable jt = new JTable(data, column);
                jt.setBounds(50, 50, 300, 300);
                List<Booking> comAndAmount = bookingService.getCommissionAndAmount(startSqlDate,endSqlDate);
                GenerateMonthlyReportPdf.generatePdf(jt,comAndAmount);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (DocumentException ex) {
                throw new RuntimeException(ex);
            }
            try {
                File file = new File("Monthlyreport.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("The PDF report file does not exist!");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,200));
        frame.setSize(1500,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}