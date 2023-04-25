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
    private int vid;
    private Date bookingDate;
    private Double price;
    private String bookingStatus;
}
