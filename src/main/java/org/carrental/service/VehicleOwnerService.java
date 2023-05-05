package org.carrental.service;

import org.carrental.dao.VehicleOwnerDAO;
import org.carrental.domain.Vehicle;
import org.carrental.domain.VehicleOwner;

import java.sql.Date;
import java.util.List;

public class VehicleOwnerService {
    VehicleOwnerDAO dao = new VehicleOwnerDAO();
    public String[][] searchByName(String name){
        List<VehicleOwner> vehicleOwnerList = dao.getByName(name);
        return transformToJTable(vehicleOwnerList,5);
    }

    public String[][] getAllVehicleOwnerForJTable(){
        List<VehicleOwner> vehicleOwnerList = dao.getAll();
        return transformToJTable(vehicleOwnerList,6);
    }

    private String[][] transformToJTable(List<VehicleOwner> vehicleOwnerList,int columnSize){
        String[][] data = new String[vehicleOwnerList.size()][columnSize];

        for (int i = 0; i < vehicleOwnerList.size() ; i++) {
            data[i][0] = String.valueOf(vehicleOwnerList.get(i).getID());
            data[i][1] = vehicleOwnerList.get(i).getO_name();
            data[i][2] = vehicleOwnerList.get(i).getPhoneNumber();
            data[i][3] = vehicleOwnerList.get(i).getCnic();
            data[i][4] = vehicleOwnerList.get(i).getAddress();
            data[i][5] = Float.toString(vehicleOwnerList.get(i).getCommission());

        }
        return data;
    }
    private String[][] transformToJTableForReports(List<VehicleOwner> vehicleOwnerList,int columnSize){
        String[][] data = new String[vehicleOwnerList.size()][columnSize];

        for (int i = 0; i < vehicleOwnerList.size() ; i++) {
            data[i][0] = String.valueOf(vehicleOwnerList.get(i).getID());
            data[i][1] = vehicleOwnerList.get(i).getO_name();
            data[i][2] = vehicleOwnerList.get(i).getPhoneNumber();
            data[i][3] = vehicleOwnerList.get(i).getAddress();
            data[i][4] = String.valueOf(vehicleOwnerList.get(i).getCommission());
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
    public void delete(String id){
        dao.deleteByID(Long.parseLong(id));
    }
    public void update(String name, Long id) {
        dao.updateUI(name,id);
    }

    public String[][] getTotalCommission(Date startDate, Date endDate) {
        List<VehicleOwner> vehicleOwnerList = dao.getTotalOwnerCommission(startDate,endDate);
        return  transformToJTableForReports(vehicleOwnerList,5);
    }

    public String[] getOwnerDataForComboBox(String id) {
        List<VehicleOwner> vehicleOwnerList =dao.getDataForComboBox(id);
        return convertVehicleListToArray(vehicleOwnerList);
    }

    private String[] convertVehicleListToArray(List<VehicleOwner> vehicleOwnerList) {
        String[] data = new String[vehicleOwnerList.size()];

        for (int i = 0; i < vehicleOwnerList.size(); i++) {
            data[i] = vehicleOwnerList.get(i).getID()+","+vehicleOwnerList.get(i).getO_name();
        }
        return data;
    }

}
