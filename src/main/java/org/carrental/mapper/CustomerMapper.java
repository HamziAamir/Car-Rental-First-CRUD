package org.carrental.mapper;

import com.mysql.cj.protocol.Resultset;
import org.carrental.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerMapper implements IMapper{
    private final static String ID = "id";
    private final static String NAME = "c_name";
    private final static String PHONE_NUMBER = "phone_number";
    private final static String CNIC = "cnic";
    private final static String ADDRESS = "address";
    private final static String REFERENCE_PHONE_NUMBER = "ref_phonenumber";


    @Override
    public List resultSetToList(ResultSet rs) throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        while(rs.next()){
            Customer customer = Customer.builder()
                    .id ((long) rs.getInt(ID))
                    .name(rs.getString(NAME))
                    .phoneNumber(rs.getString(PHONE_NUMBER))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .referencePhoneNumber(rs.getString(REFERENCE_PHONE_NUMBER))
                    .build();
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer resultSetToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Customer.builder()
                    .id ((long) rs.getInt(ID))
                    .name(rs.getString(NAME))
                    .phoneNumber(rs.getString(PHONE_NUMBER))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .referencePhoneNumber(rs.getString(REFERENCE_PHONE_NUMBER))
                    .build();
        }
        else{
            return null;
        }
    }
}
