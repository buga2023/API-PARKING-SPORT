package com.example.apivagasestacionamento.Service;

import com.example.apivagasestacionamento.DTOS.ParkingSportDTOS;
import com.example.apivagasestacionamento.Repository.ParkingRepository;
import com.example.apivagasestacionamento.models.ParkingSportModel;
import jakarta.transaction.Transactional;
import org.hibernate.validator.constraints.UUID;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSportService {
    final ParkingRepository parkingRepository;

    public ParkingSportService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }
    @Transactional
    public ParkingSportModel save(ParkingSportModel parkingSportModel){
        return parkingRepository.save(parkingSportModel);
    }
    public boolean existsByLicensePlateCar(String licensePlateCar){
        return parkingRepository.existsByLicensePlatecar(licensePlateCar);
    }
    public boolean existsByParkingSpotNumber(String ParkingSportNumber){
        return  parkingRepository.existsByParkingSpotNumber(ParkingSportNumber);
    }
    public boolean existsByApartmentAndBlock(String ApartmentAndBlock,  String block){
        return  parkingRepository.existsByApartmentAndBlock(ApartmentAndBlock, block);
    }
    public List<ParkingSportModel> findAll(){
        return parkingRepository.findAll();
    }

    public Optional<ParkingSportModel> findById(UUID id) {
        return parkingRepository.findById(id);
    }
    @Transactional
    public void delete(ParkingSportModel parkingSportModel) {
       parkingRepository.delete(parkingSportModel);
    }
}
