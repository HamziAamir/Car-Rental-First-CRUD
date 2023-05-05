package org.carrental.mapper;

import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleOwnerMapper implements IMapper<VehicleOwner>{

    public static final String ID = "id";
    public static final String O_NAME = "o_name";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String CNIC = "cnic";
    public static final String ADDRESS = "address";
    public static final String COMMISION = "commision";


    @Override
    public List<VehicleOwner> resultSetToList(ResultSet rs) throws SQLException {
        List<VehicleOwner> vehicleOwnerList = new ArrayList<>();
        while (rs.next()){
            VehicleOwner vehicleOwner = VehicleOwner.builder()
                    .ID(rs.getInt(ID))
                    .o_name(rs.getString(O_NAME))
                    .phoneNumber(rs.getString(PHONE_NUMBER))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .commission(rs.getFloat(COMMISION))
                    .build();
            vehicleOwnerList.add(vehicleOwner);
        }
        return vehicleOwnerList;
    }

    public List<VehicleOwner> ResultSetToList(ResultSet rs) throws SQLException {

        List<VehicleOwner> vehicleOwnerList = new ArrayList<>();
        while (rs.next()) {
            VehicleOwner vehicleOwner = VehicleOwner.builder()
                    .ID((int) rs.getLong(ID))
                    .o_name(rs.getString(O_NAME))
                    .phoneNumber(rs.getString(PHONE_NUMBER))
                    .address(rs.getString(ADDRESS))
                    .commission(rs.getFloat(COMMISION))
                    .build();
            vehicleOwnerList.add(vehicleOwner);
        }
        return vehicleOwnerList;
    }


    @Override
    public VehicleOwner resultSetToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return VehicleOwner.builder()
                    .ID(rs.getInt(ID))
                    .o_name(rs.getString(O_NAME))
                    .phoneNumber(rs.getString(PHONE_NUMBER))
                    .cnic(rs.getString(CNIC))
                    .address(rs.getString(ADDRESS))
                    .commission(rs.getFloat(COMMISION))
                    .build();
        }else{
            return null;
        }
    }

}
