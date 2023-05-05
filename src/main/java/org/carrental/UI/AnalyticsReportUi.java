package org.carrental.UI;

import com.itextpdf.text.DocumentException;
import org.carrental.domain.Booking;
import org.carrental.service.BookingService;
import org.carrental.service.DateLabelFormatter;
import org.carrental.service.GenerateCarAnalyticsPdf;
import org.carrental.service.GenerateCarAvailabilityPdf;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class AnalyticsReportUi {
    BookingService bookingService = new BookingService();
    AnalyticsReportUi(){
        JFrame frame = new JFrame("Analytics Report");


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

        generatePdfBtn.addActionListener((event)->{
            try {
//            List totalAmountAndCommission = (List) bookingService.getTotalCommissionAndAmount();
//            String[][] data1 = bookingService.getMaxCardata();
//            String[][] data2 = bookingService.getMinCardata();

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

                String[] column = {"Max Car ID,Max Car Name", "Min Car ID Min Car Name", "Max Profit","Max Commission"};
                JTable jt = new JTable(bookingService.getMinMaxCarData(bookingService.getMinCardata(startSqlDate, endSqlDate), bookingService.getMaxCardata(startSqlDate, endSqlDate), startSqlDate, endSqlDate), column);
                jt.setBounds(50, 50, 300, 300);
                GenerateCarAnalyticsPdf.generatePdf(jt);
            }

                catch(FileNotFoundException ex){
                    throw new RuntimeException(ex);
                } catch(DocumentException ex){
                    throw new RuntimeException(ex);
                }

                try {
                    File file = new File("AnalyticsReport.pdf");
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
