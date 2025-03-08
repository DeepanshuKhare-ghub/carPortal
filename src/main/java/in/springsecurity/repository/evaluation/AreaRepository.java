package in.springsecurity.repository.evaluation;

import in.springsecurity.entity.evaluation.Agent;
import in.springsecurity.entity.evaluation.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {

    @Query("SELECT a FROM Area a WHERE a.pinCode = :pinCode")
    Optional<Area> findByPinCode(@Param("pinCode")Integer pinCode);

    @Query("SELECT a FROM Area a WHERE a.pinCode = :pinCode")
    List<Area> findAreaByPinCode(Integer pinCode);

}