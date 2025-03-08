package in.springsecurity.service.evaluation;

import in.springsecurity.entity.evaluation.Agent;
import in.springsecurity.entity.evaluation.Area;
import in.springsecurity.entity.evaluation.CustomerVisit;
import in.springsecurity.repository.evaluation.AgentRepository;
import in.springsecurity.repository.evaluation.AreaRepository;
import in.springsecurity.repository.evaluation.CustomerVisitRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CRMService {

    private AreaRepository areaRepository;
    private AgentRepository agentRepository;
    private CustomerVisitRepository customerVisitRepository;
    public CRMService(AreaRepository areaRepository, AgentRepository agentRepository, CustomerVisitRepository customerVisitRepository) {
        this.areaRepository = areaRepository;
        this.agentRepository = agentRepository;
        this.customerVisitRepository = customerVisitRepository;
    }


    public List<Area> getAllAgents(Integer pinCode) {
        List<Area> areas = areaRepository.findAreaByPinCode(pinCode);
        return  areas;
    }


    public String updateAgent(Long customerId, Long agentId) {
        Optional<Agent> byId = agentRepository.findById(agentId);
        Agent agent = null;
        if(byId.isPresent()){
            agent = byId.get();
        }
        CustomerVisit customerVisit = null;
        Optional<CustomerVisit> optionalCustomerVisit = customerVisitRepository.findById(customerId);
        if(optionalCustomerVisit.isPresent()){
            customerVisit = optionalCustomerVisit.get();
        }
        customerVisit.setAgent(agent);
        customerVisitRepository.save(customerVisit);
        return "Agent is now allocated";

    }
}
