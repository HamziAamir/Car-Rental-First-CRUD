package org.carrental.UI;

import javax.swing.*;
import java.awt.*;

public class HomeUI {

    public HomeUI(){
        JFrame frame = new JFrame("Car Rental-Home");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,200,40));
        JButton customerbtn = new JButton("Customer");
        JButton vehiclebtn = new JButton("Vehicle");
        JButton vehicleownerbtn = new JButton("Vehicle Owner");
        JButton bookingbtn = new JButton("Booking");
        JButton reportsbtn = new JButton("Reports");

        JButton userbtn = new JButton("User");
        JButton logoutbtn = new JButton("Logout");


        frame.add(customerbtn);
        frame.add(vehiclebtn);
        frame.add(vehicleownerbtn);
        frame.add(bookingbtn);
        frame.add(reportsbtn);
        frame.add(userbtn);
        frame.add(logoutbtn);

        frame.setSize(1000,700);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        customerbtn.addActionListener((event)->{
            frame.dispose();
            new CustomerUI();
        });
        logoutbtn.addActionListener((event)->{
            frame.dispose();
            new LOGINUI();
        });
        vehiclebtn.addActionListener((event)->{
            frame.dispose();
            new VehicleUI();
        });
        vehicleownerbtn.addActionListener((event)->{
            frame.dispose();
            new VehicleOwnerUI();
        });
        userbtn.addActionListener((event)->{
            frame.dispose();
            new UserUI();
        });
        bookingbtn.addActionListener((event)->{
            frame.dispose();
            new BookingUI();
        });
        reportsbtn.addActionListener((event)->{
            frame.dispose();
            new ReportsUI();
        });

    }
}
