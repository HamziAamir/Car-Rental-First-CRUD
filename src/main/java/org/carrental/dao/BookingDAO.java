package org.carrental.dao;

import org.carrental.domain.Booking;
import org.carrental.mapper.BookingMapper;
import org.carrental.mapper.CustomerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carrental.dao.SqlQueryConstant.*;

public class BookingDAO extends BaseDAO implements ICrud<Booking>{
    private final BookingMapper bookingMapper = new BookingMapper();
    @Override
    public void insert(Booking obj) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(INSERT_BOOKING);
            ps.setInt(1, obj.getCid());
            ps.setInt(2, obj.getVid());
            ps.setDate(3, obj.getBookingDate());
            ps.setDouble(4, obj.getPrice());
            ps.setString(5, obj.getBookingStatus());

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

    }

    @Override
    public void deleteByID(Long id) {

    }
}
