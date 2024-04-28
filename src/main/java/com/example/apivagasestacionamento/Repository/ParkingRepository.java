package com.example.apivagasestacionamento.Repository;

import com.example.apivagasestacionamento.models.ParkingSportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ParkingRepository extends JpaRepository<ParkingSportModel, UUID> {
    boolean existsByLicensePlatecar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String ParkingSportNumber);
    boolean existsByApartmentAndBlock(String ApartmentAndBlock, String Block);

    Optional<ParkingSportModel> findById(org.hibernate.validator.constraints.UUID id);
}
