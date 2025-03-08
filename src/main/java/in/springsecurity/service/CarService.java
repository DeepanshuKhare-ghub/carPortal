package in.springsecurity.service;

import in.springsecurity.entity.cars.*;
import in.springsecurity.payload.carDto.*;
import in.springsecurity.repository.cars.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private BrandRepository brandRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final ModelRepository modelRepository;
    private final YearRepository yearRepository;
    private final Transmission_TypeRepository transmission_TypeRepository;
    private final CarRepository carRepository;
    private final CarStatusRepository carStatusRepository;

    public CarService(BrandRepository brandRepository,
                      FuelTypeRepository fuelTypeRepository,
                      ModelRepository modelRepository,
                      YearRepository yearRepository,
                      Transmission_TypeRepository transmission_TypeRepository,
                      CarRepository carRepository, CarStatusRepository carStatusRepository) {
        this.brandRepository = brandRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.modelRepository = modelRepository;
        this.yearRepository = yearRepository;
        this.transmission_TypeRepository = transmission_TypeRepository;
        this.carRepository = carRepository;
        this.carStatusRepository = carStatusRepository;
    }


    public String addBrand(BrandDto brandDto) {
        Optional<CarBrand> byBrandName = brandRepository.findByBrandName(brandDto.getName());
        if(byBrandName.isPresent()){
            throw new RuntimeException("CarBrand name already exists");
        }
        {
            CarBrand carBrand = new CarBrand();
            carBrand.setBrand(brandDto.getName());
            CarBrand saved = brandRepository.save(carBrand);
            return "carBrand saved successfully";
        }
    }


    public String addFuelType(FuelDto fuelDto) {

        Optional<CarFuelType> byFuelType = fuelTypeRepository.findByFuelType(fuelDto.getType());
        if(byFuelType.isPresent()){
            throw new RuntimeException("Fuel type already exists");
        }
        {
            CarFuelType carFuelType = new CarFuelType();
            carFuelType.setFuelType(fuelDto.getType());
            CarFuelType saved = fuelTypeRepository.save(carFuelType);
            return "Fuel type saved successfully";
        }
    }


    public String addModel(ModelDto modelDto) {

        Optional<CarModel> byModel = modelRepository.findByModel(modelDto.getModel());
        if(byModel.isPresent()){
            throw new RuntimeException("model already exists");
        }
        {
            CarModel carModel = new CarModel();
            carModel.setModel(modelDto.getModel());
            CarModel saved = modelRepository.save(carModel);
            return "carModel saved successfully";
        }
    }


    public String addTransmissionType(TransmissionDto transmissionDto) {

        Optional<CarTransmission> byTransmissionType = transmission_TypeRepository
                                                          .findByTransmissionType(transmissionDto.getType());

        if(byTransmissionType.isPresent()){
            throw new RuntimeException("transmission type already exists");
        }
        {
            CarTransmission carTransmission = new CarTransmission();
            carTransmission.setType(transmissionDto.getType());
            CarTransmission save = transmission_TypeRepository.save(carTransmission);
            return "CarTransmission type saved successfully";
        }
    }



    public String addStatus(StatusDto statusDto) {
        Optional<CarStatus> byStatusName = carStatusRepository
                .findByStatusName(statusDto.getStatus());

        if(byStatusName.isPresent()){
            throw new RuntimeException("status already exists");
        }
        {
            CarStatus status = new CarStatus();
            status.setStatus(statusDto.getStatus());
            CarStatus save = carStatusRepository.save(status);
            return "Status saved successfully";
        }
    }


    public String addYear(YearDto yearDto) {

        Optional<CarYear> byYear = yearRepository.findByYear(yearDto.getYear());
        if(byYear.isPresent()){
            throw new RuntimeException(" year already exists");
        }
        {
            CarYear carYear = new CarYear();
            carYear.setYear(yearDto.getYear());
            CarYear save = yearRepository.save(carYear);
            return "carYear saved successfully";
        }
    }


    public ResponseEntity<Car> addCar(Long brandId, Long modelId, Long yearId, Long fuelId, Long transmissionId, Long statusId) {
        //checking whether the data is there in parent

        CarBrand carBrand = brandRepository.findById(brandId).orElseThrow(() -> new RuntimeException("CarBrand not found"));
        CarModel carModel = modelRepository.findById(modelId).orElseThrow(() -> new RuntimeException("CarModel not found"));
        CarYear carYear = yearRepository.findById(yearId).orElseThrow(() -> new RuntimeException("CarYear not found"));
        CarFuelType carFuelType = fuelTypeRepository.findById(fuelId).orElseThrow(() -> new RuntimeException("Fuel type not found"));
        CarTransmission carTransmission = transmission_TypeRepository.findById(transmissionId).orElseThrow(() -> new RuntimeException("carTransmission type not found"));
        CarStatus carStatus = carStatusRepository.findById(statusId).orElseThrow(()-> new RuntimeException("status not found"));

/*
AVAILABLE, SOLD , UPCOMING
 */
        Car car = new Car();
        car.setBrand(carBrand);
        car.setModel(carModel);
        car.setFuelType(carFuelType);
        car.setYear(carYear);
        car.setTransmission(carTransmission);

        if(!carStatus.getStatus().equals("AVAILABLE")){
            carStatus.setStatus("AVAILABLE");
            car.setStatus(carStatus);
        }

//        CarStatus availableStatus = carStatusRepository.findByStatusName("AVAILABLE")
//                .orElseGet(() -> {
//                    CarStatus newStatus = new CarStatus();
//                    newStatus.setStatus("AVAILABLE");
//                    return carStatusRepository.save(newStatus); // Agar nahi mila toh "Available" status insert karega
//                });
        car.setStatus(carStatus);
        Car save = carRepository.save(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public List<Car> findByParam(String param){
        try{
            int year = Integer.parseInt(param);
            return carRepository.searchCarbyYear(year);
        }
        catch (Exception e){
           return carRepository.searchCarbyParam(param);
        }
    }



    // updating the car status
    public String updateCarStatus(Long carId, Long statusId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        CarStatus carStatus = carStatusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));

        if (car.getStatus().equals(carStatus)) {
            return "Car is already in status: " + carStatus.getStatus();
        }

        car.setStatus(carStatus);
        carRepository.save(car);

        return "Car status updated to: " + carStatus.getStatus();
    }

}
