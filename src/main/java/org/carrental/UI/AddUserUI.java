package org.carrental.UI;

import org.carrental.service.UserService;

import javax.swing.*;
import java.awt.*;

public class AddUserUI {

    private final UserService userService = new UserService();
    public AddUserUI(){
        JFrame frame = new JFrame("Car Rental APP - ADD USER ");
        frame.setLayout(new GridLayout(3,4,10,10));

        JLabel usernameLb = new JLabel("USERNAME");
        JTextField usernameTf = new JTextField(20);

        JLabel passwordLb = new JLabel("PASSWORD");
        JTextField passwordTf = new JTextField(20);

        JButton back = new JButton("BACK");
        JButton save = new JButton("SAVE");

        frame.add(usernameLb);
        frame.add(usernameTf);
        frame.add(passwordLb);
        frame.add(passwordTf);
        frame.add(save);
        frame.add(back);

        back.addActionListener(e->{
            frame.dispose();
            new UserUI();
        });

        save.addActionListener(e->{

            try {
                userService.save(usernameTf.getText(), passwordTf.getText());
                frame.dispose();
                new UserUI();

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
