package in.springsecurity.service.evaluation;

import in.springsecurity.entity.evaluation.Agent;
import in.springsecurity.entity.evaluation.Area;
import in.springsecurity.payload.evaluationDto.AgentDto;
import in.springsecurity.repository.evaluation.AgentRepository;
import in.springsecurity.repository.evaluation.AreaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentService {

    private final AgentRepository agentRepository;


    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;

    }


    public ResponseEntity<Agent> saveAgent(AgentDto agentDto) {
        Optional<Agent> existingAgent = agentRepository.findByName(agentDto.getName());
        if (existingAgent.isPresent()) {
            throw new RuntimeException("Agent already exists");
        }

        Agent agent = new Agent();
        agent.setEmailId(agentDto.getEmailId());
        agent.setMobile(agentDto.getMobile());
        agent.setName(agentDto.getName());



        Agent savedAgent = agentRepository.save(agent);

        return new ResponseEntity<>(savedAgent, HttpStatus.CREATED);
    }

}
