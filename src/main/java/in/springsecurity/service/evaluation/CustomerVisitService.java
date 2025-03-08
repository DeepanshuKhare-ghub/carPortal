package in.springsecurity.service.evaluation;

import in.springsecurity.entity.evaluation.CustomerVisit;
import in.springsecurity.model.Mapping;
import in.springsecurity.payload.evaluationDto.CustomerVisitDto;
import in.springsecurity.repository.evaluation.CustomerVisitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerVisitService {

    private CustomerVisitRepository customerVisitRepository;
    private Mapping mapping;

    public CustomerVisitService(CustomerVisitRepository customerVisitRepository, Mapping mapping) {

        this.customerVisitRepository = customerVisitRepository;
        this.mapping = mapping;
    }

    public ResponseEntity<CustomerVisitDto> saveCustomerVisit(CustomerVisitDto customerVisitDto) {
        Optional<CustomerVisit> customerVisitOp = customerVisitRepository.findByMobile(customerVisitDto.getMobile());
        if(customerVisitOp.isPresent()){
            throw new RuntimeException("Customer already had a visit scheduled");
        }

        //mapping
        CustomerVisit customerVisit = mapping.customerVisit(customerVisitDto);
        customerVisitRepository.save(customerVisit);

        //mapStruct mapping
        CustomerVisitDto customerVisitDto1 = mapping.customerVisitDto(customerVisit);

        return new ResponseEntity<>(customerVisitDto1 , HttpStatus.CREATED);
    }

}

