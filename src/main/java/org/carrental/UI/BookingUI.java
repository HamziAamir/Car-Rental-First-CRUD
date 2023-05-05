package org.carrental.UI;

import org.carrental.service.BookingService;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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


        String [][] data= bookingService.getAllBookingForJTable();
        String [] column={"ID","Customer ID","Vehicle ID","Booking Date","Complete Date","Price","Booking Status"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(data,column);
        JTable jt=new JTable(data,column);
        JScrollPane sp=new JScrollPane(jt);
        centerpanel.add(sp,BorderLayout.CENTER);

        closebt.addActionListener(e->{
            int selectedRowIndex = jt.getSelectedRow();
            if(selectedRowIndex >= 0 ){
                String status = (String) jt.getValueAt(selectedRowIndex,6);
                String id = (String) jt.getValueAt(selectedRowIndex,0);
                String date = (String) jt.getValueAt(selectedRowIndex,3);
                if(status.equalsIgnoreCase("Active")){
                    new CompleteBookingUi(id,date);
                    frame.dispose();
                    DefaultTableModel defaultTableModel1=new DefaultTableModel(bookingService.getAllBookingForJTable(),column);
                    jt.setModel(defaultTableModel1);
                }else {
                    JOptionPane.showMessageDialog(frame,"The booking is already completed");
                }
            }else{
                JOptionPane.showMessageDialog(frame,"Please select a field");
            }
        });
        editbt.addActionListener(e -> {
            int selectedRowIndex = jt.getSelectedRow();
            if (selectedRowIndex < 0) {
                JOptionPane.showMessageDialog(frame, "Please select a record from table");
            } else {
                String selectedStatus = (String) jt.getValueAt(selectedRowIndex, 6);
                if(selectedStatus.equalsIgnoreCase("Active")) {
                    String selectedId = (String) jt.getValueAt(selectedRowIndex, 0);
                    String cId = (String) jt.getValueAt(selectedRowIndex, 1);
                    String vId = (String) jt.getValueAt(selectedRowIndex, 2);
                    String selectedBookingDate = (String) jt.getValueAt(selectedRowIndex, 3);
                    String selectedAmount = (String) jt.getValueAt(selectedRowIndex, 5);
                    new EditBookingUi(selectedId, selectedBookingDate, selectedAmount , cId, vId);
                    DefaultTableModel defaultTableModel1 = new DefaultTableModel(bookingService.getAllBookingForJTable(), column);
                    jt.setModel(defaultTableModel1);
                    frame.dispose();
                }else {
                    JOptionPane.showMessageDialog(frame,"Complete booking cannot be updated");
                }
            }
        });
        deletebt.addActionListener(e->{
            int selectedRowIndex = jt.getSelectedRow();
            if(selectedRowIndex < 0){
                JOptionPane.showMessageDialog(frame,"Please select a record from the table");
            }else {
                String selectedId = (String) jt.getValueAt(selectedRowIndex,0);
                defaultTableModel.removeRow(selectedRowIndex);
                bookingService.setBookingInactive(Long.valueOf(selectedId));
                DefaultTableModel defaultTableModel1=new DefaultTableModel(bookingService.getAllBookingForJTable(),column);
                jt.setModel(defaultTableModel1);
                JOptionPane.showMessageDialog(frame,"Record has been Inactivated successfully");
            }
        });
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

        backbt.addActionListener((event)->{
            frame.dispose();
            new HomeUI();
        });
    }
}
