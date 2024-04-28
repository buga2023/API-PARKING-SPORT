package com.example.apivagasestacionamento.DTOS;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ParkingSportDTOS {
    private   @NotBlank String parkingSportNumber;
    private @NotBlank @Size(max = 7) String licensePlateCar;
     private  @NotBlank String brandCar;
    private  @NotBlank String modelCar;
   private  @NotBlank String colorCar;
   private  @NotBlank String responsibleName;
    private @NotBlank String Apartment;
    private @NotBlank String Block;
}

