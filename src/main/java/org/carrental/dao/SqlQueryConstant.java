package org.carrental.dao;

public class SqlQueryConstant {
    //CUSTOMER QUERIES
    public final static String GET_ALL_CUSTOMER = "select * from customer";
    public final static String GET_CUSTOMER_BY_ID = "select * from customer where id = ?";
    public final static String UPDATE_CUSTOMER_BY_ID = "update customer set c_name = ?  where id = ?";
    public final static String DELETE_CUSTOMER_BY_ID = "delete from customer where id = ?";

    public final static String INSERT_CUSTOMER =
            "insert into customer(c_name,phone_number,cnic,address,ref_phonenumber)"+
            "values (?,?,?,?,?)";

    // VEHICLE QUERIES
    public final static String INSERT_VEHICLE =
            "insert into vehicle(v_name,model,brand,color,owner_id)"+
                    "values (?,?,?,?,?)";
    public final static String GET_ALL_VEHICLE = "select * from vehicle";
    public final static String GET_VEHICLE_BY_ID = "select * from vehicle where id = ?";
    public final static String UPDATE_VEHICLE_BY_ID = "update vehicle set v_name = ?  where id = ?";
    public final static String DELETE_VEHICLE_BY_ID = "delete from vehicle where id = ?";

    //VEHICLE OWNER
    public final static String INSERT_VEHICLE_OWNER =
            "insert into vehicle_owner(o_name,phone_number,cnic,address,commision)"+
                    "values (?,?,?,?,?)";
    public final static String GET_ALL_VEHICLE_OWNER = "select * from vehicle_owner";
    public final static String GET_VEHICLE_OWNER_BY_ID = "select * from vehicle_owner where id = ?";
    public final static String UPDATE_VEHICLE_OWNER_BY_ID = "update vehicle_owner set o_name = ?  where id = ?";
    public final static String DELETE_VEHICLE_OWNER_BY_ID = "delete from vehicle_owner where id = ?";

    //USER QUERIES
    public final static String GET_USER_BY_USERNAME_PASSWORD = "select * from user where username = ? and pass = ?";
    public final static String INSERT_USER =
            "insert into user(username,pass)"+
                    "values (?,?)";
    public final static String GET_ALL_USER = "select * from user";
    public final static String GET_USER_BY_ID = "select * from user where id = ?";
    public final static String UPDATE_USER_BY_ID = "update user set username = ?  where id = ?";
    public final static String DELETE_USER_BY_ID = "delete from user where id = ?";

}
