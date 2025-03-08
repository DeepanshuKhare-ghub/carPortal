package in.springsecurity.repository.cars;

import in.springsecurity.entity.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {


    @Query("SELECT c from Car c "
                           +" JOIN c.carBrand b "
                                +" JOIN c.carTransmission t "
                                          +" JOIN c.carFuelType f "
                                             +" JOIN c.carModel m "+
    " WHERE b.name=:param OR t.type =:param OR f.type =:param OR m.model=:param")
    List<Car> searchCarbyParam(@Param("param")String param);


    @Query("SELECT c FROM Car c WHERE c.carYear.year >= :year")
    List<Car> searchCarbyYear(@Param("year") int year);


}