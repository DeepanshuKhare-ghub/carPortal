package in.springsecurity.service.evaluation;

import in.springsecurity.entity.evaluation.Agent;
import in.springsecurity.entity.evaluation.Area;
import in.springsecurity.exceptions.DuplicateResourceException;
import in.springsecurity.exceptions.ResourceNotFoundException;
import in.springsecurity.payload.evaluationDto.AreaDto;
import in.springsecurity.repository.evaluation.AgentRepository;
import in.springsecurity.repository.evaluation.AreaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AreaService {

    private final AreaRepository areaRepository;
    private final AgentRepository agentRepository;

    public AreaService(AreaRepository areaRepository, AgentRepository agentRepository) {
        this.areaRepository = areaRepository;
        this.agentRepository = agentRepository;
    }

    public ResponseEntity<AreaDto> addArea(AreaDto areaDto) {
        // Fetch the existing area by pinCode (should return only ONE)
        Optional<Area> optionalArea = areaRepository.findByPinCode(areaDto.getPinCode());
        if (optionalArea.isPresent()) {
            throw new DuplicateResourceException("Area already exists with pinCode : " + areaDto.getPinCode());
        }


        // Fetch the agent
        Optional<Agent> agentOptional = agentRepository.findById(areaDto.getAgentId());
        if (agentOptional.isEmpty()) {
            throw new ResourceNotFoundException("Agent with ID " + areaDto.getAgentId() + " not found");
        }

        Agent agent = agentOptional.get();
        Area area = new Area();
        area.setPincode(areaDto.getPinCode());
        area.setAgent(agent);
        areaRepository.save(area);
        // Return response
        AreaDto responseDto = new AreaDto();
        responseDto.setPinCode(area.getPinCode());
        responseDto.setAgentId(agent.getId());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
