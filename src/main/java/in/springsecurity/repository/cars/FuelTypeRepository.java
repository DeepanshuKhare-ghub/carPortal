package in.springsecurity.repository.cars;

import in.springsecurity.entity.cars.CarFuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FuelTypeRepository extends JpaRepository<CarFuelType, Long> {

    @Query("SELECT f from CarFuelType f where f.type = :type")
    Optional<CarFuelType> findByFuelType(@Param("type") String type);
}