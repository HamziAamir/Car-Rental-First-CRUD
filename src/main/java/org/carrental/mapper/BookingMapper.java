package org.carrental.mapper;

import org.carrental.domain.Booking;
import org.carrental.domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingMapper implements IMapper<Booking> {
    private final static String ID = "id";
    private final static String CUSTOMER_ID = "cid";
    private final static String VEHICLE_ID = "vid";
    private final static String BOOKING_DATE = "booking_date";
    private final static String PRICE = "price";
    private final static String BOOKING_STATUS = "booking_status";
    private final static String COMPLETE_DATE = "complete_date";
    public static final String V_NAME = "v_name";

    @Override
    public List<Booking> resultSetToList(ResultSet rs) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while(rs.next()){
            Booking booking = Booking.builder()
                    .id (rs.getInt(ID))
                    .cid(rs.getInt(CUSTOMER_ID))
                    .vid(rs.getInt(VEHICLE_ID))
                    .bookingDate(rs.getDate(BOOKING_DATE))
                    .completeDate(rs.getDate(COMPLETE_DATE))
                    .price(rs.getDouble(PRICE))
                    .bookingStatus(rs.getString(BOOKING_STATUS))
                    .build();
            bookingList.add(booking);
        }
        return bookingList;
    }

    @Override
    public Booking resultSetToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Booking.builder()
                    .id (rs.getInt(ID))
                    .cid(rs.getInt(CUSTOMER_ID))
                    .vid(rs.getInt(VEHICLE_ID))
                    .bookingDate(rs.getDate(BOOKING_DATE))
                    .price(rs.getDouble(PRICE))
                    .bookingStatus(rs.getString(BOOKING_STATUS))
                    .build();
        }
        else{
            return null;
        }
    }

    public List<Booking> ResultSetToListOfCommissionAndAmount(ResultSet rs) throws SQLException {
        List<Booking> commissionAndAmount = new ArrayList<>();
        if (rs.next()) {
            Booking booking = Booking.builder()
                    .vid(rs.getInt(VEHICLE_ID))
                    .vehicleName(rs.getString(V_NAME))
                    .commission(rs.getInt("total_commission"))
                    .totalAmount(rs.getInt("total_amount"))
                    .build();

            commissionAndAmount.add(booking);
            return commissionAndAmount;
        }
        return null;
    }
    public List<Booking> ResultSetToListOfCommissionandAmount(ResultSet rs) throws SQLException {
        List<Booking> commissionAndAmount = new ArrayList<>();
        if (rs.next()) {
            Booking booking = Booking.builder()

                    .commission(rs.getInt("total_commission"))
                    .totalAmount(rs.getInt("total_amount"))
                    .build();

            commissionAndAmount.add(booking);
            return commissionAndAmount;
        }
        return null;
    }
    public List<Booking> MaxMinCarsToList(ResultSet rs) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while(rs.next()){
            Booking booking = Booking.builder()
                    .vid(rs.getInt("vid"))
                    .vehicleName((rs.getString(V_NAME)))
                    .build();
            bookingList.add(booking);
        }
        return bookingList;
    }

    public List<Booking> ResultSetToListOfVehicleYearlyReport(ResultSet rs) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        while(rs.next()){
            Booking booking = Booking.builder()
                    .id(rs.getInt((ID)))
                    .bookingDate(rs.getDate(BOOKING_DATE))
                    .completeDate(rs.getDate(COMPLETE_DATE))
                    .price(rs.getDouble(PRICE))
                    .totalAmount(rs.getInt("totalamount"))
                    .commission(rs.getInt("total_commission"))
                    .build();
            bookingList.add(booking);
        }
        return bookingList;
    }
}
