package org.carrental.dao;

import org.carrental.domain.Booking;
import org.carrental.domain.Vehicle;
import org.carrental.mapper.BookingMapper;
import org.carrental.mapper.CustomerMapper;
import org.carrental.mapper.VehicleMapper;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.carrental.dao.SqlQueryConstant.*;

public class BookingDAO extends BaseDAO implements ICrud<Booking>{
    private final BookingMapper bookingMapper = new BookingMapper();
    @Override
    public void insert(Booking obj) {

        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_BOOKING);
            ps.setInt(1, obj.getCid());
            ps.setInt(2, obj.getVid());
            ps.setDate(3, obj.getBookingDate());
            ps.setDouble(4, obj.getPrice());


            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Booking> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_BOOKINGS);
            return bookingMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Booking getById(Long id) {
        return null;
    }

    @Override
    public void update(Booking obj, Long id) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.UPDATE_BOOKING_BY_ID);
            ps.setInt(1,obj.getCid());
            ps.setInt(2,obj.getVid());
            ps.setDate(3,obj.getBookingDate());
            ps.setDouble(4,obj.getPrice());
            ps.setInt(5,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(Long id) {

    }

    public void setBookingComplete(Long id , Date date){
        try {
            PreparedStatement ps = conn.prepareStatement("update booking set booking_status = 'Complete' , complete_date = ? where id = ? ");
            ps.setDate(1,date);
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setBookingInactive(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement("update booking set booking_status = 'Inactive' where id = ? ");
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getByDateRange(Date startDate, Date endDate) {
        try {
            PreparedStatement ps= conn.prepareStatement(SqlQueryConstant.GET_BY_DATE_RANGE);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs=ps.executeQuery();
            return bookingMapper.resultSetToListForMonthlyReport(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getCommissionAndAmount(Date startDate, Date endDate) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_COMMISSION_AND_AMOUNT);
            ps.setDate(1,startDate);
            ps.setDate(2,endDate);
            ResultSet rs = ps.executeQuery();
            return bookingMapper.ResultSetToListOfCommissionandAmount(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getMaxBookingOfCar(Date startDate , Date endDate) {
        try {
            PreparedStatement ps = conn.prepareStatement(MAX_CAR_BOOKING);

//            ps.setDate(1,startDate);
//            ps.setDate(2,endDate);
            ResultSet rs = ps.executeQuery();
            return bookingMapper.MaxMinCarsToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Booking> getMinBookingOfCar(Date startDate , Date endDate) {
        try {
            PreparedStatement ps = conn.prepareStatement(MIN_CAR_BOOKING);

//            ps.setDate(1,startDate);
//            ps.setDate(2,endDate);
            ResultSet rs = ps.executeQuery();
            return bookingMapper.MaxMinCarsToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getTotalCommissionAndAmount(Date startDate , Date endDate) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_TOTAL_COMMISSION_AND_TOTAL_AMOUNT);
            ps.setDate(1,startDate);
            ps.setDate(2,endDate);
            ResultSet rs = ps.executeQuery();
            return bookingMapper.ResultSetToListOfCommissionAndAmount(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Booking> getMaxCommissionData(Date startDate , Date endDate) {
        try {

            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.MAX_COMMISSION);
            ps.setDate(1,startDate);
            ps.setDate(2,endDate);
            ResultSet rs = ps.executeQuery();

            return bookingMapper.ResultSetToListOfCommissionAndAmount(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Booking> getDataForVehicleYearlyReport(String id, String startDate, String endDate) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select b.id , b.booking_date,b.complete_date,b.price ,  DATEDIFF(b.complete_date ,b.booking_date)*b.price as totalamount, o.commision*(DATEDIFF(b.complete_date, b.booking_date)*b.price)/100 as total_commission from booking b inner join vehicle v on v.id=b.vid inner join vehicle_owner o on o.id = v.owner_id where b.vid = ? AND (b.complete_date Between ? And ?) AND b.booking_status = 'Complete'");
        ps.setString(1,id);
        ps.setDate(2, Date.valueOf(startDate));
        ps.setDate(3, Date.valueOf(endDate));
        ResultSet rs = ps.executeQuery();

        return bookingMapper.ResultSetToListOfVehicleYearlyReport(rs);
    }

    public List<Booking> getDataForOwnerYearlyReport(String id, String startDate, String endDate) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select b.id ,b.vid,v.v_name as bookingStatus,b.booking_date,b.complete_date,b.price,\n" +
                "DATEDIFF(b.complete_date ,b.booking_date)*b.price as totalamount,\n" +
                "o.commision*(DATEDIFF(b.complete_date, b.booking_date)*b.price)/100 as total_commission from booking b \n" +
                "inner join vehicle v on v.id = b.vid \n" +
                "inner join vehicle_owner o on o.id = v.owner_id \n" +
                "where o.id = ? AND (b.complete_date Between ? And ? ) AND b.booking_status = 'Complete' ;");
        ps.setString(1,id);
        ps.setDate(2, Date.valueOf(startDate));
        ps.setDate(3, Date.valueOf(endDate));
        ResultSet rs = ps.executeQuery();

        return bookingMapper.ResultSetToListOfOwnerYearlyReport(rs);
    }

    public List<Booking> getAllforTable() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("\n" +
                    "select b.id,b.cid,c.c_name,b.vid,v.v_name,b.booking_date,b.complete_date,b.price,b.booking_status from booking b inner join vehicle v on v.id = b.vid inner join customer c on b.cid = c.id where b.booking_status != 'Inactive'");
            return bookingMapper.resultSetToListforTable(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}