package in.springsecurity.repository.cars;

import in.springsecurity.entity.cars.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CarStatusRepository extends JpaRepository<CarStatus, Long> {

    @Query("SELECT c FROM CarStatus c WHERE c.status = :statusName")
    Optional<CarStatus> findByStatusName(@Param("statusName") String statusName);

}