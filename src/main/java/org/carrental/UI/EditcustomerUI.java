package org.carrental.UI;

import org.carrental.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class EditcustomerUI {
    private final CustomerService customerService = new CustomerService();
    EditcustomerUI(){
        JFrame frame = new JFrame("Car Rental APP - EDIT CUSTOMER NAME ");
        frame.setLayout(new GridLayout(6,4,10,10));

        JLabel idLb = new JLabel("ID");
        JTextField idTf = new JTextField(20);

        JLabel nameLb = new JLabel("NAME");
        JTextField nameTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton edit = new JButton("EDIT");

        frame.add(idLb);
        frame.add(idTf);
        frame.add(nameLb);
        frame.add(nameTf);
        frame.add(edit);
        frame.add(back);

        back.addActionListener(e->{
            frame.dispose();
            new CustomerUI();
        });

        edit.addActionListener(e->{

            try {
                customerService.update(nameTf.getText(), Long.valueOf(idTf.getText()));
                frame.dispose();
                new CustomerUI();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,"Unable to edit");
            }

        });

        frame.setSize(500,500);        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
