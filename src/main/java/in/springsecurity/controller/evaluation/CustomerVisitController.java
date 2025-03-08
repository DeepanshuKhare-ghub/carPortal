package in.springsecurity.controller.evaluation;

import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.payload.evaluationDto.CustomerVisitDto;
import in.springsecurity.service.evaluation.CustomerVisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiEndpoints.BASE_CUSTOMER_VISIT)
public class CustomerVisitController {

    private CustomerVisitService customerVisitService;

    public CustomerVisitController(CustomerVisitService customerVisitService) {
        this.customerVisitService = customerVisitService;
    }

    @PostMapping(ApiEndpoints.AREA_CUSTOMER_VISIT_URL)
    public ResponseEntity<CustomerVisitDto> addArea(@RequestBody CustomerVisitDto customerVisitDto){
        return customerVisitService.saveCustomerVisit(customerVisitDto);
    }
}
