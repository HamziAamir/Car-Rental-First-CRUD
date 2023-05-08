package org.carrental.dao;


import org.carrental.domain.VehicleOwner;
import org.carrental.mapper.VehicleOwnerMapper;

import java.sql.*;
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
    public List<VehicleOwner> getByName(String name) {
        try {
            PreparedStatement ps = conn.prepareStatement("select * from vehicle_owner where o_name like '%"+name+"%'");
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.resultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateUI(String name, Long id) {
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE_VEHICLE_OWNER_BY_ID);
            ps.setString(1,name);
            ps.setInt(2,id.intValue());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<VehicleOwner> getTotalOwnerCommission(Date startDate, Date endDate) {
        try {
            PreparedStatement ps = conn.prepareStatement(SqlQueryConstant.GET_TOTAL_COMMISSION);
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.ResultSetToListForCommissionReport(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<VehicleOwner> getDataForComboBox(String id) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT vo.*\n" +
                    "FROM vehicle_owner vo\n" +
                    "WHERE vo.id IN (\n" +
                    "  SELECT DISTINCT owner_id\n" +
                    "  FROM vehicle\n" +
                    "  WHERE owner_id = ?\n" +
                    ")");
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            return vehicleOwnerMapper.ResultSetToList(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
