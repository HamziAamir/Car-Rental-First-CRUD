package org.carrental.UI;

import javax.swing.*;
import java.awt.*;

public class ReportsUI {

    ReportsUI(){
        JFrame frame = new JFrame("Reports");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,200,40));

        JButton monthlyReportsbtn = new JButton("Monthly Reports");
        JButton commissionReportbtn = new JButton("Commission Reports");
        JButton carAvailabilityReportbtn = new JButton("Car Availability Reports");
        JButton analyticsReportbtn = new JButton("Analytics Reports");
        JButton yearlyReportbtn = new JButton("Yearly Reports");
        JButton backbtn = new JButton("BACK");

        frame.add(monthlyReportsbtn);
        frame.add(commissionReportbtn);
        frame.add (carAvailabilityReportbtn);
        frame.add(analyticsReportbtn);
        frame.add(yearlyReportbtn);
        frame.add(backbtn);


        frame.setSize(1000,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        monthlyReportsbtn.addActionListener((event)->{
            frame.dispose();
            new MonthlyReportUi();
        });
        commissionReportbtn.addActionListener((event)->{
            frame.dispose();
            new CommissionReportUi();
        });
        carAvailabilityReportbtn.addActionListener((event)->{
            frame.dispose();
            new CarAvailabilityReportUi();
        });
        analyticsReportbtn.addActionListener((event)->{
            frame.dispose();
            new AnalyticsReportUi();
        });
        yearlyReportbtn.addActionListener((event)->{
            frame.dispose();
            new YearlyReportUi();
        });
        backbtn.addActionListener((event)->{
            frame.dispose();
            new ReportsUI();
        });
    }
}
