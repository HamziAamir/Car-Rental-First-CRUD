package org.carrental.UI;

import com.itextpdf.text.DocumentException;
import org.carrental.service.DateLabelFormatter;
import org.carrental.service.GenerateCommissionReportPdf;
import org.carrental.service.VehicleOwnerService;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class CommissionReportUi {

    VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();
    public CommissionReportUi(){


        JFrame frame = new JFrame("Commission Report");

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
        JButton backBtn = new JButton("BACK");
        frame.add(startDatePicker);
        frame.add(endDatePicker);
        frame.add(generatePdfBtn);
        frame.add(backBtn);



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
                String[][] data = vehicleOwnerService.getTotalCommission(startSqlDate,endSqlDate);
                String[] column = {"ID", "OWNER\nNAME","VEHICLE NAME", "PHONE\nNUMBER", "ADDRESS", "DAYS","PRICE","COMMI-\nSSION","Total\nAmount","COMMI-\nSSION\nGIVEN"};
                JTable jt = new JTable(data, column);
                jt.setBounds(50, 50, 300, 300);


                GenerateCommissionReportPdf.generatePdf(jt,startDatePicker.getJFormattedTextField().getText(),endDatePicker.getJFormattedTextField().getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (DocumentException ex) {
                throw new RuntimeException(ex);
            }

            try {
                File file = new File("CommissionReport.pdf");
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

        backBtn.addActionListener((event->{
            frame.dispose();
            new ReportsUI();
        }));
    }

}

