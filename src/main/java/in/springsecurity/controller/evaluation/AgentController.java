package in.springsecurity.controller.evaluation;

import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.entity.evaluation.Agent;
import in.springsecurity.payload.evaluationDto.AgentDto;
import in.springsecurity.service.evaluation.AgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.BASE_EVAL_URL)
public class AgentController {

   private AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }


    @PostMapping(ApiEndpoints.AGENT_ADD_URL)
    public ResponseEntity<Agent> addAgent(@RequestBody AgentDto agentDto){
        return agentService.saveAgent(agentDto);
    }
}
