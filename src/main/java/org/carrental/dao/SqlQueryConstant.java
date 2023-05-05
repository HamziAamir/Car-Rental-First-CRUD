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
    // Booking Queries
    public final static String GET_ALL_BOOKINGS = "select * from booking where booking_status='Active' or booking_status = 'Complete'";

    public final static String DELETE_BOOKING_BY_ID = "delete from booking where id = ?";

    public final static String INSERT_BOOKING =
            "insert into booking(cid,vid,booking_date,price)"+
                    "values (?,?,?,?)";
    public final static String UPDATE_BOOKING_BY_ID =
            "update booking set cid = ? , vid = ? , booking_date = ? , price = ? where id = ? ";
    public final static  String GET_BY_DATE_RANGE = "select b.id , c.id as cid , v.id as vid" +
            " , b.booking_date , b.complete_date , (DATEDIFF(b.complete_date , b.booking_date))*b.price as price," +
            " b.booking_status\n" + "from booking b\n" +
            "inner join customer c on b.cid = c.id\n" +
            "inner join vehicle v on b.vid = v.id\n" +
            "where (b.booking_date Between ? And ?) AND b.booking_status = 'Complete'";
    public final static String GET_COMMISSION_AND_AMOUNT = "select sum(DATEDIFF(b.complete_date , " +
            "b.booking_date)*b.price) as total_amount , Sum(o.commision*(DATEDIFF(b.complete_date ," +
            " b.booking_date)*b.price)/100) as total_commission  from booking b \n" +
            "    inner join vehicle v on v.id=b.vid\n" +
            "    inner join vehicle_owner o on o.id = v.owner_id\n" +
            "    where (b.booking_date Between ? And ?) AND b.booking_status = 'Complete' ";
    public static final String GET_TOTAL_COMMISSION = "select o.id , o.o_name , o.phone_number, o.address , sum(b.price*o.commision/100) as commision from booking b\n" +
            "inner join vehicle v on v.id = b.vid\n" +
            "inner join vehicle_owner o on v.owner_id = o.id\n" +
            "where (b.booking_date between ? and ?) and b.booking_status = 'Complete'\n" +
            "group by o.id, o.o_name";
    public static final String GET_AVAILABLE_VEHICLE ="select v.id , v.v_name , v.model , v.brand ,v.color , owner_id  from vehicle v\n" +
            "left join booking b on b.vid = v.id\n" +
            "where (b.vid is null or b.booking_status = 'Complete')\n" +
            "group by v.id";
    public static final String MAX_CAR_BOOKING = "SELECT v.id as vid,v.v_name FROM vehicle v WHERE v.id = ( SELECT min(b.vid) FROM booking b WHERE (b.booking_status = 'Complete'))";
    public static final String MIN_CAR_BOOKING = "SELECT v.id as vid,v.v_name FROM vehicle v WHERE v.id = ( SELECT MAX(b.vid) FROM booking b WHERE (b.booking_status = 'Complete'))";

    public final static String GET_TOTAL_COMMISSION_AND_TOTAL_AMOUNT = "select b.vid,v.v_name, DATEDIFF(b.complete_date ,b.booking_date)*b.price AS total_amount,\n" +
            "o.commision*(DATEDIFF(b.complete_date, b.booking_date)*b.price)/100 as total_commission\n" +
            "from booking b inner join vehicle v on v.id=b.vid \n" +
            "inner join vehicle_owner o on o.id = v.owner_id  \n" +
            "where (b.booking_date Between ? And ?) AND b.booking_status = 'Complete'";

    public final static String MAX_COMMISSION = "select o.id as vid,o.o_name as v_name, DATEDIFF(b.complete_date ,b.booking_date)*b.price AS total_amount,\n" +
            "o.commision*(DATEDIFF(b.complete_date, b.booking_date)*b.price)/100 as total_commission\n" +
            "from booking b inner join vehicle v on v.id=b.vid \n" +
            "inner join vehicle_owner o on o.id = v.owner_id  \n" +
            "where (b.booking_date Between ? And ?) AND b.booking_status = 'Complete'";
}

