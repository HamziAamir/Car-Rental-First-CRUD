package org.carrental.UI;

import com.itextpdf.text.DocumentException;
import org.carrental.service.BookingService;
import org.carrental.service.GenerateYearlyReportPdf;
import org.carrental.service.VehicleOwnerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class YearlyReportUi {
    VehicleService vehicleService = new VehicleService();
    VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();
    BookingService bookingService = new BookingService();
    YearlyReportUi() {
        JFrame frame = new JFrame("Yearly Report");
        JPanel eastpanel = new JPanel(new GridLayout(4,1,50,50));
        JPanel centerpanel = new JPanel(new FlowLayout());
        JPanel northpanel = new JPanel(new FlowLayout());
        frame.setLayout(new BorderLayout());
        JLabel yearlyReport = new JLabel("YEARLY REPORTS");
        northpanel.add(yearlyReport);
        JRadioButton vehicleRb=new JRadioButton("A) Vehicle");
        JRadioButton ownerRb=new JRadioButton("B) Owner");
        JButton backbt = new JButton("BACK");
        JButton refreshbt = new JButton("Refresh");

        vehicleRb.setVisible(true);
        ownerRb.setVisible(true);
        centerpanel.add(vehicleRb);
        centerpanel.add(ownerRb);

        String[] vehicleOptions = vehicleService.getVehicleDataForComboBox();
        JComboBox<String> vehicleComboBox = new JComboBox<>(vehicleOptions);
        vehicleComboBox.setSize(20,10);
        vehicleComboBox.setVisible(false);

        String[] ownerOptions = vehicleOwnerService.getOwnerDataForYearlyReportComboBox();
        JComboBox<String> ownerRbComboBox = new JComboBox<>(ownerOptions);
        ownerRbComboBox.setSize(20,10);
        ownerRbComboBox.setVisible(false);

        Integer [] yearOptions = {2023,2022,2021,2020,2019};
        JComboBox<Integer> yearComboBox = new JComboBox<>(yearOptions);
        yearComboBox.setSize(20,10);
        yearComboBox.setVisible(false);

        JButton genPDFbtn = new JButton("Generate PDF");
        genPDFbtn.setVisible(false);

        JButton genPDFbtn1 = new JButton("Generate PDF");
        genPDFbtn1.setVisible(false);

        JButton selectbtn = new JButton("Select");
        selectbtn.setVisible(false);

        centerpanel.add(vehicleComboBox);
        centerpanel.add(ownerRbComboBox);
        centerpanel.add(selectbtn);

        eastpanel.add(genPDFbtn);
        eastpanel.add(genPDFbtn1);
        eastpanel.add(refreshbt);
        eastpanel.add(backbt);
        vehicleRb.addActionListener((event->{
            ownerRb.setVisible(false);
            vehicleComboBox.setVisible(true);
            selectbtn.setVisible(true);
        }));
        vehicleComboBox.addActionListener((event1->{
            String vehicleInput = (String) vehicleComboBox.getSelectedItem();
            String[] vehicleParts = vehicleInput.split(",");
            String vehicleId = vehicleParts[0];
            String[] vehicleOwnerOptions = vehicleOwnerService.getOwnerDataForComboBox(vehicleService.getOwnerIdForComboBox(vehicleId));
            JComboBox<String> ownerComboBox = new JComboBox<>(vehicleOwnerOptions);
            ownerComboBox.setSize(20,10);
            ownerComboBox.setVisible(false);
            centerpanel.add(ownerComboBox);
            centerpanel.add(yearComboBox);
            selectbtn.addActionListener((event2->{
                selectbtn.setVisible(false);
                ownerComboBox.setVisible(true);


                ownerComboBox.addActionListener((event3->{
                    yearComboBox.setVisible(true);
                    genPDFbtn.setVisible(true);
                }));
            }));
        }));

        Integer [] yearOptions1 = {2023,2022,2021,2020,2019};
        JComboBox<Integer> yearComboBox1 = new JComboBox<>(yearOptions1);
        yearComboBox1.setSize(20,10);
        yearComboBox1.setVisible(false);
        centerpanel.add(yearComboBox1);
        ownerRb.addActionListener((event->{

            vehicleRb.setVisible(false);
            ownerRbComboBox.setVisible(true);
        }));
        ownerRbComboBox.addActionListener((event)->{
            centerpanel.add(yearComboBox);
            yearComboBox1.setVisible(true);
        });
        yearComboBox1.addActionListener((event)->{
            genPDFbtn1.setVisible(true);
        });

        genPDFbtn.addActionListener((event3->{
            int year = (int) yearComboBox1.getSelectedItem();
            String [] date = bookingService.getDateByYear(year);
            String vehicleInput = (String) vehicleComboBox.getSelectedItem();
            String[] vehicleParts = vehicleInput.split(",");
            String vehicleId = vehicleParts[0];
            String vehicleName = vehicleParts[1];
            String[][] data1 = bookingService.getDataForVehicleYearlyReport(vehicleId,date[0],date[1]);
            int totalAmountCommissionAndProfit [] = bookingService.getProfitForYearly(vehicleId,date[0],date[1]);
            String[] column1 = {"ID", "Booking Date", "Complete Date", "Price", "Total Amount", "Commission"};
            JTable jt1 = new JTable(data1, column1);
            jt1.setBounds(50, 50, 300, 300);

            try {
                GenerateYearlyReportPdf.GenerateYearlyReportPdf(jt1 ,year,totalAmountCommissionAndProfit,"VEHICLE",vehicleName);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
            try {
                File file = new File("YearlyReport.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("The PDF report file does not exist!");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }));

        genPDFbtn1.addActionListener((event)->{
            int year = (int) yearComboBox.getSelectedItem();
            String ownerInput = (String) ownerRbComboBox.getSelectedItem();
            String[] ownerParts = ownerInput.split(",");
            String ownerId = ownerParts[0];
            String ownerName = ownerParts[1];
             String [] date = bookingService.getDateByYear(year);
            String[][] data1 = bookingService.getDataForOwnerYearlyReport(ownerId,date[0],date[1]);
            int totalAmountCommissionAndProfit [] = bookingService.getOwnerProfitForYearly(ownerId,date[0],date[1]);
            String[] column1 = {"ID","Vehicle ID","Vehicle Name","Booking Date", "Complete Date", "Price", "Total Amount", "Commission"};
            JTable jt1 = new JTable(data1, column1);
            jt1.setBounds(50, 50, 300, 300);

            try {
                GenerateYearlyReportPdf.GenerateYearlyReportPdf(jt1 ,year,totalAmountCommissionAndProfit,"OWNER",ownerName);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
            try {
                File file = new File("YearlyReport.pdf");
                if (file.exists()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("The PDF report file does not exist!");
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        });
        refreshbt.addActionListener((event->{
            frame.dispose();
            new YearlyReportUi();
        }));

        backbt.addActionListener((event->{
            frame.dispose();
            new ReportsUI();
        }));

        frame.add(northpanel,BorderLayout.NORTH);
        frame.add(centerpanel,BorderLayout.CENTER);
        frame.add(eastpanel,BorderLayout.EAST);
        centerpanel.setVisible(true);
        centerpanel.setVisible(true);

        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}