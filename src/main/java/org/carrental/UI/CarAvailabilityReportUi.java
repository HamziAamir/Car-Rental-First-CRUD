package org.carrental.UI;

import com.itextpdf.text.DocumentException;
import org.carrental.service.GenerateCarAvailabilityPdf;
import org.carrental.service.VehicleService;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class CarAvailabilityReportUi {

    VehicleService vehicleService = new VehicleService();
    public CarAvailabilityReportUi(){
        JFrame frame = new JFrame("Monthly Report");


        UtilDateModel startModel = new UtilDateModel();
        startModel.setSelected(true);
        UtilDateModel endModel = new UtilDateModel();
        endModel.setSelected(true);

        JButton generatePdfBtn = new JButton("Generate PDF");
        frame.add(generatePdfBtn);


        generatePdfBtn.addActionListener(e->{
            try {
                String[][] data = vehicleService.getAvailableVehicles();
                String[] column = {"ID", "VEHICLE NAME", "COLOR", "YEAR", "BRAND" , "OWNER ID"};
                JTable jt = new JTable(data, column);
                jt.setBounds(50, 50, 300, 300);

                GenerateCarAvailabilityPdf.generatePdf(jt);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (DocumentException ex) {
                throw new RuntimeException(ex);
            }

            try {
                File file = new File("CarAvailabilityReport.pdf");
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
