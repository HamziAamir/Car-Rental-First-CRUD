package org.carrental;

import org.carrental.UI.CustomerUI;
import org.carrental.UI.HomeUI;
import org.carrental.UI.LOGINUI;
import org.carrental.dao.CustomerDAO;
import org.carrental.dao.UserDAO;
import org.carrental.dao.VehicleDAO;
import org.carrental.dao.VehicleOwnerDAO;
import org.carrental.domain.Customer;
import org.carrental.domain.User;
import org.carrental.domain.Vehicle;

public class Main {
    public static void main(String[] args) {
         CustomerDAO customerDAO = new CustomerDAO();
    /*    customerDAO.getAll().forEach(System.out::println);
        Customer newCus = Customer.builder()
                .name("HAMZI")
                .address("TawakkalMansion")
                .phoneNumber("1515634654")
                .cnic("654654351")
                .referencePhoneNumber("65132132")
                .build();
        customerDAO.insert(newCus);
        customerDAO.getAll().forEach(System.out::println);
        System.out.println(customerDAO.getById(2L));
        Customer customer = customerDAO.getById(2L);
        customer.setName("hehe");
        customerDAO.update(customer,2L);
        customerDAO.getAll().forEach(System.out::println);
        customerDAO.deleteByID(4L);
        System.out.println(customerDAO.getAll());*/
        VehicleDAO vehicleDAO = new VehicleDAO();
      /*      Vehicle newVeh = Vehicle.builder()
                    .v_name("Toyota Corolla")
                    .model("2020")
                    .brand("Toyota")
                    .color("Gold")
                    .ownerId("2")
                    .build();
            vehicleDAO.insert(newVeh);
        vehicleDAO.getAll().forEach(System.out::println);
        System.out.println(vehicleDAO.getById(2L));
        Vehicle vehicle = vehicleDAO.getById(2L);
        vehicle.setV_name("hehe");
        vehicleDAO.update(vehicle,2L);
        vehicleDAO.getAll().forEach(System.out::println);
        vehicle.setV_name("SONATA");
        vehicleDAO.update(vehicle,2L);
        vehicleDAO.getAll().forEach(System.out::println);
        vehicleDAO.deleteByID(4L);
        vehicleDAO.getAll().forEach(System.out::println);*/
//        VehicleOwnerDAO vehicleOwnerDAO = new VehicleOwnerDAO();
//        vehicleOwnerDAO.getAll().forEach(System.out::println);
        /*UserDAO userDAO = new UserDAO();
        userDAO.getAll().forEach(System.out::println);*/
        new LOGINUI();
    }
}