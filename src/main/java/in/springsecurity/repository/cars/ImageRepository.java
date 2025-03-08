package in.springsecurity.repository.cars;

import in.springsecurity.entity.cars.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<CarImage, Long> {

    @Query("SELECT c.url FROM CarImage c WHERE c.car.id =:carId")
    List<String> findByCarId(@Param("carId") long carId);
}

