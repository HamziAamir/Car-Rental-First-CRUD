package org.carrental.service;

import org.carrental.dao.VehicleDAO;
import org.carrental.domain.Vehicle;


import java.util.List;

public class VehicleService {
    VehicleDAO dao = new VehicleDAO();
    public String[][] searchByName(String name){
        List<Vehicle> vehicleList = dao.getByName(name);
        return transformToJTable(vehicleList,5);
    }

    public String[][] getAllVehicleForJTable(){
        List<Vehicle> vehicleList = dao.getAll();
        return transformToJTable(vehicleList,6);
    }

    private String[][] transformToJTable(List<Vehicle> vehicleList,int columnSize){
        String[][] data = new String[vehicleList.size()][columnSize];

        for (int i = 0; i < vehicleList.size() ; i++) {
            data[i][0] = String.valueOf(vehicleList.get(i).getId());
            data[i][1] = vehicleList.get(i).getV_name();
            data[i][2] = vehicleList.get(i).getModel();
            data[i][3] = vehicleList.get(i).getBrand();
            data[i][4] = vehicleList.get(i).getColor();
            data[i][5] = vehicleList.get(i).getOwnerId();

        }
        return data;
    }
    public void save(String name, int model, String brand, String color, String id) {

        Vehicle vehicle = Vehicle.builder()
                .v_name(name)
                .model(String.valueOf(model))
                .brand(brand)
                .color(color)
                .ownerId(id)
                .build();

        dao.insert(vehicle);


    }
    public void delete(String id){
        dao.deleteByID(Long.parseLong(id));
    }
}
