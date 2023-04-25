package org.carrental.service;



import org.carrental.dao.BookingDAO;
import org.carrental.domain.Booking;

import java.util.List;
public class BookingService {
    BookingDAO dao = new BookingDAO();

    public String[][] getAllCustomerForJTable(){
        List<Booking> bookingList = dao.getAll();
        return transformToJTable(bookingList,6);
    }

    private String[][] transformToJTable(List<Booking> bookingList,int columnSize){
        String[][] data = new String[bookingList.size()][columnSize];

        for (int i = 0; i < bookingList.size() ; i++) {
            data[i][0] = String.valueOf(bookingList.get(i).getId());
            data[i][1] = String.valueOf(bookingList.get(i).getCid());
            data[i][2] = String.valueOf(bookingList.get(i).getVid());
            data[i][3] = String.valueOf(bookingList.get(i).getBookingDate());
            data[i][4] = String.valueOf(bookingList.get(i).getPrice());
            data[i][5] = bookingList.get(i).getBookingStatus();

        }
        return data;
    }
//    public void save(Long cid, Long vid, String bookingDate, double price, String bookingStatus) {
//
//        Booking booking = Booking.builder()
//                .phoneNumber(phone)
//                .cnic(cnic)
//                .name(name)
//                .address(address)
//                .referencePhoneNumber(refPhoneNumber)
//                .build();
//
//        dao.insert(booking);
//    }
    public void delete(String id){
        dao.deleteByID(Long.parseLong(id));
    }

}
