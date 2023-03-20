package org.carrental.dao;

public class SqlQueryConstant {

    public final static String GET_ALL_CUSTOMER = "Select * from customer";
    public final static String GET_CUSTOMER_BY_ID = "Select * from customer where id = ?";
    public final static String UPDATE_CUSTOMER_BY_ID = "update customer set c_name = ?  where id = ?";
    public final static String DELETE_CUSTOMER_BY_ID = "delete from customer where id = ?";
    public final static String GET_USER_BY_USERNAME_PASSWORD = "select * from user where username = ? and pass = ?";
    public final static String INSERT_CUSTOMER =
            "insert into customer(c_name,phone_number,cnic,address,ref_phonenumber)"+
            "values (?,?,?,?,?)";

}
