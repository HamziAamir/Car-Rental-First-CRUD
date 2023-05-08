package org.carrental.domain;
import lombok.*;

import java.sql.Date;
import java.text.DateFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Booking {
    private int id;
    private int cid;
    private String customerName;
    private int vid;
    private String vehicleName;
    private Date bookingDate;
    private Date completeDate;
    private Double price;
    private String bookingStatus;
    private Integer totalAmount;
    private Integer commission;
    private Double total_Amount;
    private Integer days;
}
