package in.springsecurity.controller.car;



import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.entity.cars.Car;
import in.springsecurity.payload.carDto.*;
import in.springsecurity.repository.cars.CarRepository;
import in.springsecurity.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiEndpoints.CAR_URL)
public class CarDetailsController {

    private CarService carService;
    private final CarRepository carRepository;

    public CarDetailsController(CarService carService,
                                CarRepository carRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
    }

    @PostMapping(ApiEndpoints.ADD_BRAND_URL)
    public ResponseEntity<String> addCarBrand(@RequestBody BrandDto brandDto){
        String brandStatus = carService.addBrand(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(brandStatus);
    }



    @PostMapping(ApiEndpoints.ADD_FUELTYPE_URL)
    public ResponseEntity<String> addCarFuelType(@RequestBody FuelDto fuelDto){
        String type = carService.addFuelType(fuelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(type);
    }


    @PostMapping(ApiEndpoints.ADD_MODEL_URL)
    public ResponseEntity<String> addModel(@RequestBody ModelDto modelDto){
        String model = carService.addModel(modelDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PostMapping(ApiEndpoints.ADD_TRANSMISSION_URL)
    public ResponseEntity<String> addTransmissionType(@RequestBody TransmissionDto transmissionDto){
        String type = carService.addTransmissionType(transmissionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(type);
    }


    @PostMapping(ApiEndpoints.ADD_YEAR_URL)
    public ResponseEntity<String> addYear(@RequestBody YearDto yearDto){
        String year = carService.addYear(yearDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(year);
    }

    @PostMapping(ApiEndpoints.ADD_CARSTATUS_URL)
    public ResponseEntity<String> addStatus(@RequestBody StatusDto statusDto){
        String status = carService.addStatus(statusDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @PostMapping(ApiEndpoints.ADD_CAR_URL)
    public ResponseEntity<Car> addCar(@RequestBody CarDto carDto)
    {
         return carService.addCar(carDto.getBrandId(),
                carDto.getModelId(),
                carDto.getYearId(),
                carDto.getFuelId(),
                carDto.getTransmissionId(),
                 carDto.getStatusId());
    }




    //api to update status of car
    @PutMapping(ApiEndpoints.UPDATE_CAR_STATUS_URL)
    public ResponseEntity<String> updateCarStatus(@PathVariable Long carId, @RequestParam Long statusId) {
        String response = carService.updateCarStatus(carId, statusId);
        return ResponseEntity.ok(response);
    }


    //get all the cars

    @GetMapping(ApiEndpoints.GET_ALL_CARS_URL)
    public List<Car> getAllCars(){
        return carService.findAllCars();
    }


    @GetMapping(ApiEndpoints.GET_PARAM_URL)
    public List<Car> getCarByParam(@RequestParam String param){
        return  carService.findByParam(param);
    }

}
