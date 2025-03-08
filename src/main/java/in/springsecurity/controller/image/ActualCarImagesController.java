package in.springsecurity.controller.image;


import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.entity.cars.Car;
import in.springsecurity.entity.evaluation.CarEvaluationPhotos;
import in.springsecurity.repository.cars.CarRepository;
import in.springsecurity.repository.evaluation.CarEvaluationPhotosRepository;
import in.springsecurity.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiEndpoints.CAR_EVAL_URL)
public class ActualCarImagesController {

    private BucketService bucketService;
    private CarEvaluationPhotosRepository carEvaluationPhotosRepository;
    private final CarRepository carRepository;

    public ActualCarImagesController(BucketService bucketService, CarEvaluationPhotosRepository carEvaluationPhotosRepository, CarRepository carRepository) {
        this.bucketService = bucketService;
        this.carEvaluationPhotosRepository = carEvaluationPhotosRepository;
        this.carRepository = carRepository;
    }


    @PostMapping(ApiEndpoints.ADD_CAR_EVAL_IMAGES)
    public ResponseEntity<CarEvaluationPhotos> uploadActualPhotos(@RequestParam List<MultipartFile> files ,
                                   @PathVariable String bucketName,
                                   @PathVariable Long carId) {

        
        CarEvaluationPhotos carEvaluationPhotos = null;
        for (MultipartFile file : files) {
            String url = bucketService.uploadFile(file, bucketName);
            Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("No car with given id"));
            carEvaluationPhotos = new CarEvaluationPhotos();
            carEvaluationPhotos.setCar(car);
            carEvaluationPhotos.setUrl(url);
            carEvaluationPhotosRepository.save(carEvaluationPhotos);
        }
        return  new ResponseEntity<>(carEvaluationPhotos , HttpStatus.CREATED );
    }
}
