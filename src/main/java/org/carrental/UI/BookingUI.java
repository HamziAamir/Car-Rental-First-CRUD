package org.carrental.UI;

import org.carrental.service.BookingService;


import javax.swing.*;

import java.awt.*;


public class BookingUI {

    BookingService bookingService = new BookingService();

    public BookingUI(){
        JFrame frame = new JFrame("Booking DataBase");
        JPanel northpanel = new JPanel();
        JPanel eastpanel = new JPanel(new GridLayout(5,1,35,35));
        JPanel centerpanel = new JPanel(new BorderLayout(10,10));
        frame.setLayout(new BorderLayout());
        northpanel.setLayout(new FlowLayout());
       // JTextField searchtf = new JTextField(50);
        JButton addbt = new JButton("Add");
        JButton closebt = new JButton("Close");
        JButton editbt = new JButton("Edit");
        JButton deletebt = new JButton("Delete");
        JButton backbt = new JButton("BACK");

        eastpanel.add(addbt);
        eastpanel.add(closebt);
        eastpanel.add(editbt);
        eastpanel.add(deletebt);
        eastpanel.add(backbt);


        String [][] data= bookingService.getAllCustomerForJTable();
        String [] column={"ID","Customer ID","Vehicle ID","Booking Date","Price","Booking Status"};
        JTable jt=new JTable(data,column);
        JScrollPane sp=new JScrollPane(jt);
        centerpanel.add(sp,BorderLayout.CENTER);
        frame.add(centerpanel,BorderLayout.CENTER);
        frame.add(northpanel,BorderLayout.NORTH);
        frame.add(eastpanel,BorderLayout.EAST);
        frame.setSize(1000,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addbt.addActionListener(e->{
            frame.dispose();
            new AddBookingUI();
        });
//        deletebt.addActionListener(e->{
//            frame.dispose();
//            new DeleteBookingUI();
//        });

        backbt.addActionListener((event)->{
            frame.dispose();
            new HomeUI();
        });
    }
}
