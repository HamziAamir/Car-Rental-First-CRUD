package org.carrental.dao;

import org.carrental.domain.Vehicle;
import org.carrental.mapper.VehicleMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.carrental.dao.SqlQueryConstant.*;


public class VehicleDAO extends BaseDAO implements ICrud<Vehicle> {
    VehicleMapper vehicleMapper = new VehicleMapper();
    @Override
    public void insert(Vehicle obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_VEHICLE);
            ps.setString(1, obj.getV_name());
            ps.setString(2, obj.getModel());
            ps.setString(3, obj.getBrand());
            ps.setString(4, obj.getColor());
            ps.setString(5, obj.getOwnerId());

            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_VEHICLE);
            return vehicleMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vehicle getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(GET_VEHICLE_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return vehicleMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Vehicle obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE_VEHICLE_BY_ID);
            ps.setString(1,obj.getV_name());
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE_VEHICLE_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
