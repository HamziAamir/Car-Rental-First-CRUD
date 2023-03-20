package org.carrental;

import org.carrental.UI.CustomerUI;
import org.carrental.UI.HomeUI;
import org.carrental.UI.LOGINUI;
import org.carrental.dao.CustomerDAO;
import org.carrental.domain.Customer;

public class Main {
    public static void main(String[] args) {
/*         CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.getAll().forEach(System.out::println);
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

        new LOGINUI();
    }
}