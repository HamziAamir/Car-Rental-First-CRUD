package org.carrental.dao;


import com.mysql.cj.protocol.Resultset;
import org.carrental.domain.VehicleOwner;
import org.carrental.mapper.VehicleOwnerMapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.carrental.dao.SqlQueryConstant.*;

public class VehicleOwnerDAO extends BaseDAO implements ICrud<VehicleOwner>{
    VehicleOwnerMapper vehicleOwnerMapper = new VehicleOwnerMapper();
    @Override
    public void insert(VehicleOwner obj) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_VEHICLE_OWNER);
            ps.setString(1,obj.getO_name());
            ps.setString(2,obj.getPhoneNumber());
            ps.setString(3,obj.getCnic());
            ps.setString(4,obj.getAddress());
            ps.setFloat(5,obj.getCommission());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<VehicleOwner> getAll() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(GET_ALL_VEHICLE_OWNER);
            return vehicleOwnerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public VehicleOwner getById(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(GET_VEHICLE_OWNER_BY_ID);
            ps.setInt(1,id.intValue());
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.resultSetToObject(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(VehicleOwner obj, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE_VEHICLE_OWNER_BY_ID);
            ps.setString(1,obj.getO_name());
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE_VEHICLE_OWNER_BY_ID);
            ps.setInt(1,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
