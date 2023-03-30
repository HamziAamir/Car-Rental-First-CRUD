package org.carrental.UI;

import org.carrental.service.VehicleOwnerService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VehicleOwnerUI {
    VehicleOwnerService vehicleOwnerService = new VehicleOwnerService();
    public VehicleOwnerUI(){
        JFrame frame = new JFrame("Vehicle Owners DataBase");
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
        String data[][]= vehicleOwnerService.getAllVehicleOwnerForJTable();
        String column[]={"NAME","PHONE_NUMBER","CNIC","ADDRESS","Commission"};
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
            new AddVehicleOwnerUI();
        });

        backbt.addActionListener((event)->{
            frame.dispose();
            new HomeUI();
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
                String[][] data =  vehicleOwnerService.searchByName(searchtf.getText());
                DefaultTableModel dtm = new DefaultTableModel(data,column);
                jt.setModel(dtm);

            }
        });
    }
}
