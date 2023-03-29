package org.carrental.UI;

import org.carrental.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class AddCustomerUI {

    private final CustomerService customerService = new CustomerService();
    public AddCustomerUI(){
        JFrame frame = new JFrame("Car Rental APP - ADD CUSTOMER ");
        frame.setLayout(new GridLayout(6,4,10,10));

        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JLabel phoneLb = new JLabel("PHONE");
        JTextField phoneTf = new JTextField(20);

        JLabel cnicLb = new JLabel("CNIC");
        JTextField cnicTf = new JTextField(20);

        JLabel addressLb = new JLabel("ADDRESS");
        JTextField addressTf = new JTextField(20);

        JLabel refLb = new JLabel("REF_PHONE");
        JTextField refTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(phoneLb);
        frame.add(phoneTf);
        frame.add(cnicLb);
        frame.add(cnicTf);
        frame.add(addressLb);
        frame.add(addressTf);
        frame.add(refLb);
        frame.add(refTf);
        frame.add(save);
        frame.add(back);

        back.addActionListener(e->{
            frame.dispose();
            new CustomerUI();
        });

        save.addActionListener(e->{

            try {
                customerService.save(nameTf.getText(), phoneTf.getText(),
                        cnicTf.getText(), addressTf.getText(), refTf.getText());
                frame.dispose();
                new CustomerUI();

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
