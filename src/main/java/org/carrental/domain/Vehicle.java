package org.carrental.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Vehicle {
        private int id;
        private String v_name;
        private String model;
        private String brand;
        private String color;
        private String ownerId;



}
