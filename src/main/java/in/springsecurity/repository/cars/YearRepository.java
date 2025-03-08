package in.springsecurity.repository.cars;

import in.springsecurity.entity.cars.CarYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface YearRepository extends JpaRepository<CarYear, Long> {

    @Query("SELECT y from CarYear y where y.year = :year")
    Optional<CarYear> findByYear(@Param("year") Integer year);
}