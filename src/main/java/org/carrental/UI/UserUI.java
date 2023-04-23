package org.carrental.UI;

import org.carrental.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UserUI {
    UserService userService = new UserService();
    public UserUI(){
        JFrame frame = new JFrame("Users DataBase");
        JPanel northpanel = new JPanel();
        JPanel eastpanel = new JPanel(new GridLayout(4,1,50,50));
        JPanel centerpanel = new JPanel(new BorderLayout(10,10));
        frame.setLayout(new BorderLayout());
        northpanel.setLayout(new FlowLayout());
        JTextField searchtf = new JTextField(50);
        JButton addbt = new JButton("Add");
        JButton editbt = new JButton("Edit");
        JButton deletebt = new JButton("Delete");
        JButton backbt = new JButton("BACK");

        northpanel.add(searchtf);
        eastpanel.add(addbt);
        eastpanel.add(editbt);
        eastpanel.add(deletebt);
        eastpanel.add(backbt);
        String data[][]= userService.getAllUserForJTable();
        String column[]={"ID","User_Name","Passwords"};
        JTable jt=new JTable(data,column);
        JScrollPane sp=new JScrollPane(jt);
        centerpanel.add(sp,BorderLayout.CENTER);
        frame.add(centerpanel,BorderLayout.CENTER);
        frame.add(northpanel,BorderLayout.NORTH);
        frame.add(eastpanel,BorderLayout.EAST);
        frame.setSize(1000,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        addbt.addActionListener(e->{
            frame.dispose();
            new AddUserUI();
        });

        backbt.addActionListener((event)->{
            frame.dispose();
            new HomeUI();
        });
        deletebt.addActionListener(e->{
            frame.dispose();
            new DeleteUserUI();
        });

        searchtf.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {
                String[][] data =  userService.searchByName(searchtf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data,column);
                jt.setModel(dtm);

            }
        });
    }
}
