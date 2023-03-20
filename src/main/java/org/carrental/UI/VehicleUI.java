package org.carrental.UI;

import javax.swing.*;
import java.awt.*;

public class VehicleUI {
    public VehicleUI(){
        JFrame frame = new JFrame("Vehicle DataBase");
        JPanel northpanel = new JPanel();
        JPanel eastpanel = new JPanel(new GridLayout(4,1,50,50));
        JPanel centerpanel = new JPanel(new BorderLayout(10,10));
        frame.setLayout(new BorderLayout());
        northpanel.setLayout(new FlowLayout());
        JTextField searchtf = new JTextField("Search Here For your DATA");
        JButton searchbt = new JButton("Search");
        JButton addbt = new JButton("Add");
        JButton editbt = new JButton("Edit");
        JButton deletebt = new JButton("Delete");
        JButton backbt = new JButton("BACK");

        northpanel.add(searchtf);
        northpanel.add(searchbt);
        eastpanel.add(addbt);
        eastpanel.add(editbt);
        eastpanel.add(deletebt);
        eastpanel.add(backbt);
        String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};
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

        backbt.addActionListener((event)->{
            frame.dispose();
            new HomeUI();
        });
    }
}
