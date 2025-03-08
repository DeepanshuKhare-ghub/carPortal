package in.springsecurity.controller.evaluation;

import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.payload.evaluationDto.AreaDto;
import in.springsecurity.service.evaluation.AreaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiEndpoints.BASE_AREA_URL)
public class AreaController {

    private AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }

    @PostMapping(ApiEndpoints.ADD_AREA_URL)
    public ResponseEntity<AreaDto> addArea(@RequestBody AreaDto areaDto){
        return areaService.addArea(areaDto);
    }


//
//    @DeleteMapping
//    public ResponseEntity<String> deleteArea(@RequestParam Long id){
//        areaService.deleteArea(id);
//        return ResponseEntity.ok("Area deleted Successfully");
//    }

}
