package org.carrental.UI;

import org.carrental.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class DeleteCustomerUI {
    private final CustomerService customerService = new CustomerService();
    public DeleteCustomerUI(){
        JFrame frame = new JFrame("Car Rental APP - Delete CUSTOMER ");
        frame.setLayout(new GridLayout(6,4,10,10));

        JLabel idLb = new JLabel("ID");
        JTextField idTf = new JTextField(5);

        JButton back = new JButton("BACK");
        JButton delete = new JButton("DELETE");

        frame.add(idLb);
        frame.add(idTf);
        frame.add(delete);
        frame.add(back);

        back.addActionListener(e->{
            frame.dispose();
            new CustomerUI();
        });

        delete.addActionListener(e->{

            try {
                customerService.delete(idTf.getText());
                frame.dispose();
                new CustomerUI();

            }catch (Exception ex){
                JOptionPane.showMessageDialog(frame,"Unable to delete");
            }

        });

        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
