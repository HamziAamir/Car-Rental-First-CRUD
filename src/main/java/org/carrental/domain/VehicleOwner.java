package org.carrental.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class VehicleOwner {
    private int ID;
    private String o_name;
    private String phoneNumber;
    private String cnic;
    private String address;
    private float commission;


}
