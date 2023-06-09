package org.carrental.service;

import org.carrental.dao.CustomerDAO;
import org.carrental.domain.Customer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.carrental.dao.SqlQueryConstant.UPDATE_CUSTOMER_BY_ID;

public class CustomerService {
    CustomerDAO dao = new CustomerDAO();
    public String[][] searchByName(String name){
        List<Customer> customerList = dao.getByName(name);
        return transformToJTable(customerList,5);
    }

    public String[][] getAllCustomerForJTable(){
        List<Customer> customerList = dao.getAll();
        return transformToJTable(customerList,6);
    }

    private String[][] transformToJTable(List<Customer> customerList,int columnSize){
        String[][] data = new String[customerList.size()][columnSize];

        for (int i = 0; i < customerList.size() ; i++) {
            data[i][0] = String.valueOf(customerList.get(i).getId());
            data[i][1] = customerList.get(i).getName();
            data[i][2] = customerList.get(i).getPhoneNumber();
            data[i][3] = customerList.get(i).getCnic();
            data[i][4] = customerList.get(i).getAddress();
            data[i][5] = customerList.get(i).getReferencePhoneNumber();

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
    public void delete(String id){
        dao.deleteByID(Long.parseLong(id));
    }
    public void update(String name, Long id) {
        dao.updateUI(name,id);
    }
}
