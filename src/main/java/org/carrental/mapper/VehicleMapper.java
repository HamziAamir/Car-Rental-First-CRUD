package org.carrental.mapper;

import org.carrental.domain.Customer;
import org.carrental.domain.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleMapper implements IMapper<Vehicle> {
    private final static String ID = "id";
    private final static String NAME = "v_name";
    private final static String MODEL  = "model";
    private final static String BRAND = "brand";
    private final static String COLOR = "color";
    private final static String OWNER_ID = "owner_id";

    @Override
    public List<Vehicle> resultSetToList(ResultSet rs) throws SQLException {
        List<Vehicle> vehicleList = new ArrayList<>();
        while(rs.next()){
            Vehicle vehicle = Vehicle.builder()
                    .id (rs.getInt(ID))
                    .v_name(rs.getString(NAME))
                    .model(rs.getString(MODEL))
                    .brand(rs.getString(BRAND))
                    .color(rs.getString(COLOR))
                    .ownerId(rs.getString(OWNER_ID))
                    .build();
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    @Override
    public Vehicle resultSetToObject(ResultSet rs) throws SQLException {
        if(rs.next()){
            return Vehicle.builder()
                    .id (rs.getInt(ID))
                    .v_name(rs.getString(NAME))
                    .model(rs.getString(MODEL))
                    .brand(rs.getString(BRAND))
                    .color(rs.getString(COLOR))
                    .ownerId(rs.getString(OWNER_ID))
                    .build();
        }
        else{
            return null;
        }
    }
}
