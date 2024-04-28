package com.example.apivagasestacionamento.Controller;

import com.example.apivagasestacionamento.DTOS.ParkingSportDTOS;
import com.example.apivagasestacionamento.Service.ParkingSportService;
import com.example.apivagasestacionamento.models.ParkingSportModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parkingSport")
@RequiredArgsConstructor
public class ParkingController {
    final ParkingSportService parkingSportService;

    @PostMapping
    public ResponseEntity<Object> saveParkingSport(@RequestBody @Valid ParkingSportDTOS parkingSportDTOS) {
        if (parkingSportService.existsByParkingSpotNumber(parkingSportDTOS.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT:PARKING SPORT IS ALREADY IN USE ");
        }
        if (parkingSportService.existsByLicensePlateCar(parkingSportDTOS.getParkingSportNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT:LICENSE PLATE CAR IS ALREADY IN USE");
        }
        if (parkingSportService.existsByApartmentAndBlock(parkingSportDTOS.getApartment(), parkingSportDTOS.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: PARKING SPORT ALREADY REGISTERED FOR THIS APARTMENT/BLOCK");
        }


        var parkingSportModel = new ParkingSportModel();
        BeanUtils.copyProperties(parkingSportDTOS, parkingSportModel);
        parkingSportModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSportService.save(parkingSportModel));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSportModel>> getAllParkingSports() {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSportService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSportModel> parkingSportModelOptinal = parkingSportService.findById(id);
        if (!parkingSportModelOptinal.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not Fond");

        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSportModelOptinal.get());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> DeleteOneParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSportModel> parkingSportModelOptinal = parkingSportService.findById(id);
        if (!parkingSportModelOptinal.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot not Fond");

        }
        parkingSportService.delete(parkingSportModelOptinal.get());
        return ResponseEntity.status(HttpStatus.OK).body("PARKING SPOT DELETED SUCCESFULLY");

    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value="id")UUID id ,@RequestBody @Valid ParkingSportDTOS parkingSpotDTO){
        Optional<ParkingSportModel>parkingSportModelOptional =parkingSportService.findById(id);
        if(!parkingSportModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PARKING SPOT NOT FOUND ");
        }
        var parkingSpotModel =new ParkingSportModel();
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
        parkingSpotModel.setId(parkingSportModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSportModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSportService.save(parkingSpotModel));

    }
}


