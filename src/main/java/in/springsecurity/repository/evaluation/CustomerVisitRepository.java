package in.springsecurity.repository.evaluation;

import in.springsecurity.entity.evaluation.CustomerVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CustomerVisitRepository extends JpaRepository<CustomerVisit, Long> {

    @Query("SELECT c FROM CustomerVisit c Where c.mobile =:mobile ORDER BY c.dateOfVisit DESC , c.timeOfVisit DESC ")
    Optional<CustomerVisit> findByMobile(@Param("mobile") String mobile);


    // query for finding customer recent customer visits
    @Query("SELECT c FROM CustomerVisit c WHERE c.mobile = :mobile " +
            "AND CONCAT(c.dateOfVisit, 'T', c.timeOfVisit) >= :twoHoursAgo")
    Optional<CustomerVisit> findRecentVisit(@Param("mobile") String mobile,
                                            @Param("twoHoursAgo") LocalDateTime twoHoursAgo);

}