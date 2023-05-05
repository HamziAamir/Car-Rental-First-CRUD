package org.carrental.UI;

import org.carrental.service.BookingService;
import org.carrental.service.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class EditBookingUi {

    BookingService bookingService=new BookingService();

    public EditBookingUi(String id , String bookingDate , String amount , String cId , String vId) {
        JFrame frame = new JFrame("UPDATE BOOKING");

        JLabel customerLbl = new JLabel("Select Customer :");
        JLabel vehicleLbl = new JLabel("Select Vehicle :");
        JLabel dateLbl = new JLabel("Select Date :");
        JLabel amountLbl = new JLabel("Amount :");
        JTextField amountTf = new JTextField();

        String[] customerOptions = bookingService.getCustomerDataForComboBox();
        JComboBox<String> customerComboBox = new JComboBox<>(customerOptions);
        customerComboBox.setSize(20, 10);


        String[] vehicleOptions = bookingService.getVehicleDataForComboBox();
        JComboBox<String> vehicleComboBox = new JComboBox<>(vehicleOptions);
        vehicleComboBox.setSize(20, 10);

        UtilDateModel model = new UtilDateModel();
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.getJFormattedTextField().setText(bookingDate);
        JButton updateBtn = new JButton("Update");
        JButton backBtn = new JButton("Back");

        frame.add(customerLbl);
        frame.add(customerComboBox);
        frame.add(vehicleLbl);
        frame.add(vehicleComboBox);
        frame.add(dateLbl);
        frame.add(datePicker);
        frame.add(amountLbl);
        frame.add(amountTf);
        frame.add(updateBtn);
        frame.add(backBtn);


        amountTf.setText(amount);

        String customer = null ;
        for (String el:customerOptions) {
            if(el.startsWith(cId)){
                customer = el;
            }
        }

        String vehicle = null ;
        for (String el : vehicleOptions) {
            if(el.startsWith(vId)){
                vehicle = el;
            }
        }

        customerComboBox.setSelectedItem(customer);
        vehicleComboBox.setSelectedItem(vehicle);

        updateBtn.addActionListener(e -> {
            String vehicleInput = (String) vehicleComboBox.getSelectedItem();
            String[] vehicleParts = vehicleInput.split(",");
            String vehicleId = vehicleParts[0];

            String customerInput = (String) customerComboBox.getSelectedItem();
            String[] customerParts = customerInput.split(",");
            String customerId = customerParts[0];


            if (!amountTf.getText().isEmpty() || datePicker.getJFormattedTextField() == null) {
                if (amountTf.getText().matches("[0-9]*\\.?[0-9]+")) {
                    bookingService.update(id, customerId, vehicleId,datePicker.getJFormattedTextField().getText(),amountTf.getText());
                    JOptionPane.showMessageDialog(frame, "Booking Updated");
                } else {
                    JOptionPane.showMessageDialog(frame, "PLease enter the correct amount");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill all the fields");
            }

        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            new BookingUI();
        });

        frame.setLayout(new GridLayout(8, 3, 10, 30));
        frame.setSize(500, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
