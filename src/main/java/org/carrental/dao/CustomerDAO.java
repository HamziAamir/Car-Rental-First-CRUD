package org.carrental.dao;

import org.carrental.domain.Customer;
import org.carrental.mapper.CustomerMapper;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.carrental.dao.SqlQueryConstant.GET_ALL_CUSTOMER;
import static org.carrental.dao.SqlQueryConstant.INSERT_CUSTOMER;
import static org.carrental.dao.SqlQueryConstant.GET_CUSTOMER_BY_ID;
import static org.carrental.dao.SqlQueryConstant.UPDATE_CUSTOMER_BY_ID;
import static org.carrental.dao.SqlQueryConstant.DELETE_CUSTOMER_BY_ID;


public class CustomerDAO extends BaseDAO implements ICrud<Customer>{

    private final CustomerMapper customerMapper = new CustomerMapper();
    @Override
    public void insert(Customer obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_CUSTOMER);
            ps.setString(1, obj.getName());
            ps.setString(2, obj.getPhoneNumber());
            ps.setString(3, obj.getCnic());
            ps.setString(4, obj.getAddress());
            ps.setString(5, obj.getReferencePhoneNumber());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Customer> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_CUSTOMER);
            return customerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(GET_CUSTOMER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Customer obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE_CUSTOMER_BY_ID);
            ps.setString(1,obj.getName());
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE_CUSTOMER_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Customer> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from customer where c_name like '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            return customerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public void deleteByName(String name) {
//        try {
//            PreparedStatement ps = conn.prepareStatement("delete from customer where c_name = "+name);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
