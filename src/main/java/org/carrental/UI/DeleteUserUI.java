package org.carrental.UI;

import org.carrental.service.CustomerService;
import org.carrental.service.UserService;

import javax.swing.*;
import java.awt.*;

public class DeleteUserUI {
        UserService userService = new UserService();
    public DeleteUserUI(){
            JFrame frame = new JFrame("Car Rental APP - Delete User ");
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
                new UserUI();
            });

            delete.addActionListener(e->{

                try {
                    userService.delete(idTf.getText());
                    frame.dispose();
                    new UserUI();

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
