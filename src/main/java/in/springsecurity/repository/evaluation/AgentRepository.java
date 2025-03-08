package in.springsecurity.repository.evaluation;

import in.springsecurity.entity.evaluation.Agent;
import in.springsecurity.payload.evaluationDto.AgentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query("SELECT c FROM Agent c WHERE c.name = :name")
    Optional<Agent> findByName(@Param("name") String name);


}