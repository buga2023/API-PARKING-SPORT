package com.example.apivagasestacionamento.models;

import jakarta.persistence.*;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="TB_PARKING_SPORT")
@Data
public class ParkingSportModel implements Serializable {
    private static final  long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false,unique = true ,length = 20)
    private String parkingSpotNumber;
    @Column(nullable = false,unique = true ,length = 20)
    private String licensePlatecar;
    @Column(nullable = false ,length = 20)
    private String brandCar;
    @Column(nullable = false,length = 20)
    private String modelCar;
    @Column(nullable = false, length = 20)
    private String colorCar;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @Column(nullable = false ,length = 50)
    private String responsibleName;
    @Column(nullable = false ,length = 10)
    private String apartment;
    @Column(nullable = false ,length = 10)
    private String block;

}
