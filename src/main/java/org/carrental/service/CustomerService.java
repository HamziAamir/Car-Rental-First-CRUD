package org.carrental.service;

import org.carrental.dao.CustomerDAO;
import org.carrental.domain.Customer;

import java.util.List;

public class CustomerService {
    CustomerDAO dao = new CustomerDAO();
    public String[][] searchByName(String name){
        List<Customer> customerList = dao.getByName(name);
        return transformToJTable(customerList,5);
    }

    public String[][] getAllCustomerForJTable(){
        List<Customer> customerList = dao.getAll();
        return transformToJTable(customerList,5);
    }

    private String[][] transformToJTable(List<Customer> customerList,int columnSize){
        String[][] data = new String[customerList.size()][columnSize];

        for (int i = 0; i < customerList.size() ; i++) {
            data[i][0] = customerList.get(i).getName();
            data[i][1] = customerList.get(i).getPhoneNumber();
            data[i][2] = customerList.get(i).getCnic();
            data[i][3] = customerList.get(i).getAddress();
            data[i][4] = customerList.get(i).getReferencePhoneNumber();

        }
        return data;
    }

    public void save(String name, String phone, String cnic, String address, String refPhoneNumber) {

        Customer customer = Customer.builder()
                .phoneNumber(phone)
                .cnic(cnic)
                .name(name)
                .address(address)
                .referencePhoneNumber(refPhoneNumber)
                .build();

        dao.insert(customer);
    }

}
