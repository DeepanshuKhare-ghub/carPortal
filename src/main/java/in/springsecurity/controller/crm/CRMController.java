package in.springsecurity.controller.crm;

import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.entity.evaluation.Area;
import in.springsecurity.repository.evaluation.CustomerVisitRepository;
import in.springsecurity.service.evaluation.CRMService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.BASE_CRM_URL)
public class CRMController {

    private CRMService crmService;
    private final CustomerVisitRepository customerVisitRepository;


    public CRMController(CRMService crmService,
                         CustomerVisitRepository customerVisitRepository) {
        this.crmService = crmService;
        this.customerVisitRepository = customerVisitRepository;
    }

    @GetMapping(ApiEndpoints.SEARCH_AGENT_URL)
    public ResponseEntity<?> searchAgent(
          @RequestParam Integer pinCode
    ){
        List<Area> allAgents = crmService.getAllAgents(pinCode);
        return new ResponseEntity<>(allAgents, HttpStatus.OK);
    }





    @PutMapping(ApiEndpoints.ALLOCATE_AGENT_URL)
    public String allocateAgent(@RequestParam long customerId,
                               @RequestParam long agentId){
         return crmService.updateAgent(customerId , agentId);
    }

}
