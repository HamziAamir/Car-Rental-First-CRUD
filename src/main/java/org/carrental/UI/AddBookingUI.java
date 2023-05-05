package org.carrental.UI;

import org.carrental.service.BookingService;
import org.carrental.service.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class AddBookingUI {
    public static void main(String[] args) {
        AddBookingUI ab = new AddBookingUI();
    }
        private final BookingService bookingService = new BookingService();
        public AddBookingUI(){
            JFrame frame = new JFrame("Car Rental APP - ADD Booking ");
            frame.setLayout(new GridLayout(9,4,8,8));

            JLabel customerLbl= new JLabel("Select Customer :");
            JLabel vehicleLbl= new JLabel("Select Vehicle :");
            JLabel dateLbl= new JLabel("Select Date :");
            JLabel amountLbl= new JLabel("Amount :");
            String[] customerOptions = bookingService.getCustomerDataForComboBox();
            JComboBox<String> customerComboBox = new JComboBox<>(customerOptions);
            customerComboBox.setSize(20,10);

            String[] vehicleOptions = bookingService.getVehicleDataForComboBox();
            JComboBox<String> vehicleComboBox = new JComboBox<>(vehicleOptions);
            customerComboBox.setSize(20,10);

            UtilDateModel model = new UtilDateModel();
            model.setSelected(true);
            Properties properties = new Properties();
            properties.put("text.today", "Today");
            properties.put("text.month", "Month");
            properties.put("text.year", "Year");
            JDatePanelImpl datePanel = new JDatePanelImpl(model,properties);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

            JLabel price = new JLabel("Add Price");
            JTextField pricetxt = new JTextField();

            JButton backBtn = new JButton("BACK");
            JButton saveBtn = new JButton("BOOK");

            frame.add(customerLbl);
            frame.add(customerComboBox);
            frame.add(vehicleLbl);
            frame.add(vehicleComboBox);
            frame.add(datePicker);
            frame.add(price);
            frame.add(pricetxt);
            frame.add(saveBtn);
            frame.add(backBtn);

            backBtn.addActionListener(e->{
                frame.dispose();
                new BookingUI();
            });

            saveBtn.addActionListener(e->{
                String vehicleInput = (String) vehicleComboBox.getSelectedItem();
                String[] vehicleParts = vehicleInput.split(",");
                String vehicleId = vehicleParts[0];

                String customerInput = (String) customerComboBox.getSelectedItem();
                String[] customerParts = customerInput.split(",");
                String customerId = customerParts[0];


                if(!pricetxt.getText().isEmpty() || datePicker.getJFormattedTextField() == null ) {
                    if(pricetxt.getText().matches("[0-9]*\\.?[0-9]+")) {
                        bookingService.save(customerId, vehicleId, String.valueOf(datePicker.getJFormattedTextField().getText()), pricetxt.getText());
                        JOptionPane.showMessageDialog(frame,"Booking Added");
                    }else{
                        JOptionPane.showMessageDialog(frame,"PLease enter the correct amount");
                    }
                }else{
                    JOptionPane.showMessageDialog(frame,"Please fill all the fields");
                }
            });


            frame.setSize(800,500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
}
