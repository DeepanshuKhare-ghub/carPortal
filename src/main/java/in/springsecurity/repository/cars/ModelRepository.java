package in.springsecurity.repository.cars;

import in.springsecurity.entity.cars.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ModelRepository extends JpaRepository<CarModel, Long> {
    @Query("SELECT m from CarModel m where m.model = :model")
    Optional<CarModel> findByModel(@Param("model") String model);
}