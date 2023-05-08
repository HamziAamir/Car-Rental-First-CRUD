package org.carrental.UI;

import org.carrental.service.VehicleOwnerService;
import org.carrental.service.VehicleService;

import javax.swing.*;
import java.awt.*;

public class AddVehicleUI {

    private final VehicleService vehicleService = new VehicleService();
    private final VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();
    public AddVehicleUI(){
        JFrame frame = new JFrame("Car Rental APP - ADD Vehicle");
        frame.setLayout(new GridLayout(6,4,10,10));

        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel modelLb = new JLabel("Model");
        JTextField modelTf = new JTextField(20);

        JLabel brandLb = new JLabel("Brand");
        JTextField brandTf = new JTextField(20);

        JLabel colorLb = new JLabel("Color");
        JTextField colorTf = new JTextField(20);

        JLabel ownerIdLb = new JLabel("Owner id");
        String[] vehicleOwnerOptions = vehicleOwnerService.getOwnerDataForYearlyReportComboBox();
        JComboBox<String> vehicleOwnerComboBox = new JComboBox<>(vehicleOwnerOptions);
        vehicleOwnerComboBox.setSize(20,10);
        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(modelLb);
        frame.add(modelTf);
        frame.add(brandLb);
        frame.add(brandTf);
        frame.add(colorLb);
        frame.add(colorTf);
        frame.add(ownerIdLb);
        frame.add(vehicleOwnerComboBox);
        frame.add(save);
        frame.add(back);

        back.addActionListener(e->{
            frame.dispose();
            new VehicleUI();
        });

        save.addActionListener(e->{
            String vehicleOwnerInput = (String) vehicleOwnerComboBox.getSelectedItem();
            String[] vehicleOwnerParts = vehicleOwnerInput.split(",");
            String vehicleOwnerId = vehicleOwnerParts[0];
            try {
                vehicleService.save(nameTf.getText(), Integer.parseInt(modelTf.getText()),
                        brandTf.getText(), colorTf.getText(),vehicleOwnerId);
                frame.dispose();
                new VehicleUI();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,"Unable to save");
            }

        });

        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
