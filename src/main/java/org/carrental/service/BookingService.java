package org.carrental.service;



import org.carrental.dao.BookingDAO;
import org.carrental.dao.CustomerDAO;
import org.carrental.dao.VehicleDAO;
import org.carrental.domain.Booking;
import org.carrental.domain.Customer;
import org.carrental.domain.Vehicle;

import javax.xml.crypto.dsig.Transform;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class BookingService {
    BookingDAO dao = new BookingDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    VehicleDAO vehicleDAO = new VehicleDAO();

    public String[][] getAllBookingForJTable(){
        List<Booking> bookingList = dao.getAllforTable();
        return transformToJTable(bookingList,7);
    }

    private String[][] transformToJTable(List<Booking> bookingList,int columnSize){
        String[][] data = new String[bookingList.size()][columnSize];

        for (int i = 0; i < bookingList.size() ; i++) {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getCid())+","+bookingList.get(i).getCustomerName();
            data[i][2] = String.valueOf(bookingList.get(i).getVid())+","+bookingList.get(i).getVehicleName();
            data[i][3] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][4] = String.valueOf(bookingList.get(i).getCompleteDate());
            data[i][5] = String.valueOf(bookingList.get(i).getPrice());
            data[i][6] = bookingList.get(i).getBookingStatus();

        }
        return data;
    }

    public void delete(String id){
        dao.deleteByID(Long.parseLong(id));
    }

    public String[] getCustomerDataForComboBox() {
        List<Customer> customerList =customerDAO.getDataForComboBox();
        return convertCustomerListToArray(customerList);
    }

    private String[] convertCustomerListToArray(List<Customer> customerList) {
        String[] data = new String[customerList.size()];

        for (int i = 0; i < customerList.size(); i++) {
            data[i] = customerList.get(i).getId()+","+customerList.get(i).getName();
        }
        return data;
    }

    public String[] getVehicleDataForComboBox() {
        List<Vehicle> vehicleList =vehicleDAO.getDataForComboBox();
        return convertVehicleListToArray(vehicleList);
    }

    private String[] convertVehicleListToArray(List<Vehicle> vehicleList) {
        String[] data = new String[vehicleList.size()];

        for (int i = 0; i < vehicleList.size(); i++) {
            data[i] = vehicleList.get(i).getId()+","+vehicleList.get(i).getV_name();
        }
        return data;
    }

    public void save(String customerId, String vehicleId, String date, String price) {
        Booking booking= Booking.builder()
                .cid(Integer.parseInt(customerId))
                .vid(Integer.parseInt(vehicleId))
                .bookingDate(Date.valueOf(date))
                .price(Double.valueOf(price))
                .build();
        dao.insert(booking);
    }

    public void setBookingComplete(Long id , Date date){

        dao.setBookingComplete(id,date);
    }
    public void update(String id , String customer , String vehicle , String bookingDate , String amount){

        Booking booking = Booking.builder()
                .cid(Integer.parseInt(customer))
                .vid(Integer.parseInt(vehicle))
                .bookingDate(Date.valueOf(bookingDate))
                .price(Double.valueOf(amount))
                .build();

        dao.update(booking, Long.valueOf(id));
    }

    public void setBookingInactive(Long id) {
        dao.setBookingInactive(id);
    }

    public String[][] getByDateRange(Date startDate, Date endDate) {
        List<Booking> bookingList = dao.getByDateRange(startDate,endDate);
        return transformToJTableForMonthlyReports(bookingList,9);
    }

    private String[][] transformToJTableForMonthlyReports(List<Booking> bookingList, int columnSize) {
        String[][] data = new String[bookingList.size()][columnSize];

        for (int i = 0; i < bookingList.size() ; i++) {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getCid())+","+bookingList.get(i).getCustomerName();
            data[i][2] = String.valueOf(bookingList.get(i).getVid())+","+bookingList.get(i).getVehicleName();
            data[i][3] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][4] = String.valueOf(bookingList.get(i).getCompleteDate());
            data[i][5] = String.valueOf(bookingList.get(i).getPrice());
            data[i][6] = String.valueOf(bookingList.get(i).getDays());
            data[i][7] = String.valueOf(bookingList.get(i).getTotal_Amount());
            data[i][8] = bookingList.get(i).getBookingStatus();

        }
        return data;
    }

    public List<Booking> getCommissionAndAmount(Date startDate, Date endDate) {
        return dao.getCommissionAndAmount(startDate,endDate);
    }

    public String [][] getMaxCardata(Date startDate , Date endDate) {
        List<Booking> maxCarBooking = dao.getMaxBookingOfCar(startDate,endDate);
            return TransformToJTableForMinMax(maxCarBooking, 2);

    }
    public String [][] getMinCardata(Date startDate , Date endDate) {
        List<Booking> maxCarBooking = dao.getMinBookingOfCar(startDate,endDate);
            return TransformToJTableForMinMax(maxCarBooking, 2);
    }
    public List<Booking> getTotalCommissionAndAmount(Date startDate , Date endDate) {
        return dao.getTotalCommissionAndAmount(startDate,endDate);
    }
    public List<Booking> getMaxCommisionData(Date startDate , Date endDate) {
        return dao.getMaxCommissionData(startDate,endDate);
    }

//
//    public String[][] getMinMaxCarData(String[][] minCarData , String [][] maxCarData){
//        int size = minCarData.length+maxCarData.length;
//        String [][] Data = new String[1][4];
//        Data[0][0] = maxCarData[0][0];
//        Data[0][1] = maxCarData[0][1];
//        Data[0][2] = minCarData[0][0];
//        Data[0][3] = minCarData[0][1];
//        return Data;
//    }

    public String[][] getMinMaxCarData(String[][] minCarData , String [][] maxCarData, Date startDate , Date endDate){
        String [][] Data;

        try{
            Data = new String[1][4];
            Data[0][0] = maxCarData[0][0] + "," + maxCarData[0][1];
            Data[0][1] = minCarData[0][0] + "," + minCarData[0][1];
            Data[0][2] = String.valueOf(CalculateProfit(getTotalCommissionAndAmount(startDate, endDate))) + "," + MaxProfitVehicleIdAndName(getTotalCommissionAndAmount(startDate, endDate));
            Data[0][3] = String.valueOf(CalculateCommission(getMaxCommisionData(startDate, endDate))) + "," + MaxCommissionIdAndName(getTotalCommissionAndAmount(startDate, endDate));
        }catch(NullPointerException e){
            System.out.println("NullPointerException occurred: " + e.getMessage());
            return Data = new String[0][0];

        }
        return Data;
    }
    private String[][] TransformToJTableForMinMax(List<Booking> maxCarBooking, int columnSize) {
        String[][] data = new String[maxCarBooking.size()][columnSize];

        for (int i = 0; i < maxCarBooking.size() ; i++) {
            data[i][0] = String.valueOf(maxCarBooking.get(i).getVid());
            data[i][1] = String.valueOf(maxCarBooking.get(i).getVehicleName());
        }
        return data;
    }
//    Integer commission = commissionAndAmount.get(0).getCommission();
//    Integer amount = commissionAndAmount.get(0).getTotalAmount();
//    Integer profit = amount-commission;
    int CalculateProfit(List <Booking> CommissionAndProfit ){
        int profit;
        if(CommissionAndProfit != null){
            int [] prof = new int[CommissionAndProfit.size()];
            for (int i = 0 ;i<CommissionAndProfit.size(); i ++){
                prof[i] = CommissionAndProfit.get(i).getTotalAmount() - CommissionAndProfit.get(i).getCommission() ;
            }
            int max = 0;
            int maxindex = 0;
            for (int i = 0 ; i<prof.length ; i ++){
                if(prof[i] > max){
                    max= prof[i];
                    maxindex = i;
                }
            }
            profit = Arrays.stream(prof).max().getAsInt();
            return profit;
        }
        else{
          return  profit = 0;
        }

    }
    int CalculateCommission(List <Booking> CommissionAndProfit ){
        int commission;
        if(CommissionAndProfit !=null) {
            int[] comm = new int[CommissionAndProfit.size()];
            for (int i = 0; i < CommissionAndProfit.size(); i++) {
                comm[i] = CommissionAndProfit.get(i).getCommission();
            }
            int max = 0;
            int maxcommindex = 0;
            for (int i = 0; i < comm.length; i++) {
                if (comm[i] > max) {
                    max = comm[i];
                    maxcommindex = i;
                }
            }
            commission = Arrays.stream(comm).max().getAsInt();
            return commission;
        }
        else{
            return commission = 0;
        }
    }
    String MaxCommissionIdAndName(List <Booking> CommissionAndProfit){
        int commission; String nameAndId;
        if(CommissionAndProfit != null) {
            int[] comm = new int[CommissionAndProfit.size()];
            for (int i = 0; i < CommissionAndProfit.size(); i++) {
                comm[i] = CommissionAndProfit.get(i).getCommission();
            }
            int max = 0;
            int maxcommindex = 0;
            for (int i = 0; i < comm.length; i++) {
                if (comm[i] > max) {
                    max = comm[i];
                    maxcommindex = i;
                }
            }

             nameAndId = CommissionAndProfit.get(maxcommindex).getVid() + "," + CommissionAndProfit.get(maxcommindex).getVehicleName();
            return nameAndId;
        }
        else{
            return nameAndId = null;
        }
    }
    String MaxProfitVehicleIdAndName(List <Booking> CommissionAndProfit){
        int profit;
        String nameAndId;
        if(CommissionAndProfit != null){
            int [] prof = new int[CommissionAndProfit.size()];
            for (int i = 0 ;i<CommissionAndProfit.size(); i ++){
                prof[i] = CommissionAndProfit.get(i).getTotalAmount() - CommissionAndProfit.get(i).getCommission() ;
            }
            int max = 0;
            int maxindex = 0;
            for (int i = 0 ; i<prof.length ; i ++){
                if(prof[i] > max){
                    max= prof[i];
                    maxindex = i;
                }
            }
            profit = Arrays.stream(prof).max().getAsInt();
            nameAndId = CommissionAndProfit.get(maxindex).getVid()+","+CommissionAndProfit.get(maxindex).getVehicleName();
            return nameAndId;
        }
        else{
            return nameAndId = null;
        }
    }

    public String[] getDateByYear(int year) {
        String[] Dates = new String[2];
        if (year == 2023){
            Dates[0] = "2023-01-01";
            Dates[1] = "2023-12-01";
        }
        if (year == 2022){
            Dates[0] = "2022-01-01";
            Dates[1] = "2023-12-01";
        }
        if(year == 2021){
            Dates[0] = "2021-01-01";
            Dates[1] = "2023-12-01";
        }
        if(year == 2020){
            Dates[0] = "2020-01-01";
            Dates[1] = "2023-12-01";
        }
        if(year == 2019){
            Dates[0] = "2019-01-01";
            Dates[1] = "2013-12-01";
        }
        return Dates;
    }

    public String[][] getDataForVehicleYearlyReport(String id, String startDate, String endDate) {
        List<Booking> bookingList = null;
        try {
            bookingList = dao.getDataForVehicleYearlyReport(id,startDate,endDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return convertVehicleListToArrayForYearlyReport(bookingList);
    }

    public int[] getProfitForYearly(String id, String startDate, String endDate) {
        List<Booking> bookingList = null;
        try {
            bookingList = dao.getDataForVehicleYearlyReport(id,startDate,endDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int [] totalAmount = new int[bookingList.size()];
        int [] commission = new int[bookingList.size()];
        for (int i =0 ; i<bookingList.size();i++){
            totalAmount[i] = bookingList.get(i).getTotalAmount();
            commission[i] = bookingList.get(i).getCommission();
        }
        int totalAmountSum = 0,totalCommissionSum = 0;
        for (int j = 0 ; j<totalAmount.length ; j++){
            totalAmountSum += totalAmount[j];
            totalCommissionSum += commission[j];
        }

        int profit = totalAmountSum - totalCommissionSum;
        int [] data = new int[3];
        data[0] = profit;
        data[1] = totalAmountSum;
        data[2] = totalCommissionSum;
        return data;
    }

    private String[][] convertVehicleListToArrayForYearlyReport(List<Booking> bookingList) {
        String[][] data = new String[bookingList.size()][6];

        for (int i = 0; i < bookingList.size(); i++) {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][2] = String.valueOf(bookingList.get(i).getCompleteDate());
            data[i][3] = String.valueOf(bookingList.get(i).getPrice());
            data[i][4] = String.valueOf(bookingList.get(i).getTotalAmount());
            data[i][5] = String.valueOf(bookingList.get(i).getCommission());
        }
        return data;
    }

    public String[][] getDataForOwnerYearlyReport(String id, String startDate, String endDate) {
        List<Booking> bookingList = null;
        try {
            bookingList = dao.getDataForOwnerYearlyReport(id,startDate,endDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return convertOwnerListToArrayForYearlyReport(bookingList);
    }

    private String[][] convertOwnerListToArrayForYearlyReport(List<Booking> bookingList) {
        String[][] data = new String[bookingList.size()][8];

        for (int i = 0; i < bookingList.size(); i++) {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getVid());
            data[i][2] = String.valueOf(bookingList.get(i).getBookingStatus());
            data[i][3] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][4] = String.valueOf(bookingList.get(i).getCompleteDate());
            data[i][5] = String.valueOf(bookingList.get(i).getPrice());
            data[i][6] = String.valueOf(bookingList.get(i).getTotalAmount());
            data[i][7] = String.valueOf(bookingList.get(i).getCommission());
        }
        return data;
    }

    public int[] getOwnerProfitForYearly(String ownerId, String startDate, String endDate) {
        List<Booking> bookingList = null;
        try {
            bookingList = dao.getDataForOwnerYearlyReport(ownerId,startDate,endDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int [] totalAmount = new int[bookingList.size()];
        int [] commission = new int[bookingList.size()];
        for (int i =0 ; i<bookingList.size();i++){
            totalAmount[i] = bookingList.get(i).getTotalAmount();
            commission[i] = bookingList.get(i).getCommission();
        }
        int totalAmountSum = 0,totalCommissionSum = 0;
        for (int j = 0 ; j<totalAmount.length ; j++){
            totalAmountSum += totalAmount[j];
            totalCommissionSum += commission[j];
        }

        int profit = totalAmountSum - totalCommissionSum;
        int [] data = new int[3];
        data[0] = profit;
        data[1] = totalAmountSum;
        data[2] = totalCommissionSum;
        return data;
    }
}
