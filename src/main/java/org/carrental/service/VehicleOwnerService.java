package org.carrental.service;

import org.carrental.dao.VehicleOwnerDAO;
import org.carrental.domain.VehicleOwner;

import java.util.List;

public class VehicleOwnerService {
    VehicleOwnerDAO dao = new VehicleOwnerDAO();
    public String[][] searchByName(String name){
        List<VehicleOwner> vehicleOwnerList = dao.getByName(name);
        return transformToJTable(vehicleOwnerList,5);
    }

    public String[][] getAllVehicleOwnerForJTable(){
        List<VehicleOwner> vehicleOwnerList = dao.getAll();
        return transformToJTable(vehicleOwnerList,5);
    }

    private String[][] transformToJTable(List<VehicleOwner> vehicleOwnerList,int columnSize){
        String[][] data = new String[vehicleOwnerList.size()][columnSize];

        for (int i = 0; i < vehicleOwnerList.size() ; i++) {
            data[i][0] = vehicleOwnerList.get(i).getO_name();
            data[i][1] = vehicleOwnerList.get(i).getPhoneNumber();
            data[i][2] = vehicleOwnerList.get(i).getCnic();
            data[i][3] = vehicleOwnerList.get(i).getAddress();
            data[i][4] = Float.toString(vehicleOwnerList.get(i).getCommission());

        }
        return data;
    }
    public void save(String name, String phone, String cnic, String address, float commission) {

        VehicleOwner vehicleOwner = VehicleOwner.builder()
                .phoneNumber(phone)
                .cnic(cnic)
                .o_name(name)
                .address(address)
                .commission(commission)
                .build();

        dao.insert(vehicleOwner);
    }

}
