package in.springsecurity.repository.cars;

import in.springsecurity.entity.cars.CarTransmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Transmission_TypeRepository extends JpaRepository<CarTransmission, Long> {

    @Query("SELECT t from CarTransmission t where t.type = :type")
    Optional<CarTransmission> findByTransmissionType(@Param("type") String type);
}