package org.carrental.UI;

import org.carrental.service.AuthenticationService;

import javax.swing.*;

public class LOGINUI {
    private final AuthenticationService authenticationService = new AuthenticationService();
    public LOGINUI(){
        JFrame frame = new JFrame("LOGIN Page");

        JTextField user = new JTextField();
        user.setBounds(375,180,200,20);

        JTextField pass = new JTextField();
        pass.setBounds(375,220,200,20);

        JButton button1 = new JButton("LOGIN");
        button1.setBounds(375,240,200,50);

        frame.add(user);
        frame.add(pass);
        frame.add(button1);

        frame.setSize(1000,700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        button1.addActionListener((event)->{
            if(authenticationService.checkLogin(user.getText(),pass.getText())){
                frame.dispose();
                new HomeUI();
            }else{
                JOptionPane.showMessageDialog(frame,"Incorrect credentials");
            }
        });
    }
}
