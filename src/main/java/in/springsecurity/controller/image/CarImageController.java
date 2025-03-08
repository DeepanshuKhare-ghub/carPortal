package in.springsecurity.controller.image;

import in.springsecurity.config.ApiEndpoints;
import in.springsecurity.entity.cars.Car;
import in.springsecurity.entity.cars.CarImage;
import in.springsecurity.repository.cars.CarRepository;
import in.springsecurity.repository.cars.ImageRepository;
import in.springsecurity.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiEndpoints.IMAGE_BASE_URL)
public class CarImageController {

    private BucketService bucketService;
    private final CarRepository carRepository;
    private final ImageRepository imageRepository;

    public CarImageController(BucketService bucketService,
                              CarRepository carRepository, ImageRepository imageRepository) {
        this.bucketService = bucketService;
        this.carRepository = carRepository;
        this.imageRepository = imageRepository;
    }

    @PostMapping(ApiEndpoints.UPLOAD_FILE_URL)
    public ResponseEntity<CarImage> uploadCarPhotos(
            @RequestParam MultipartFile file ,
            @PathVariable String bucketName,
            @PathVariable Long carId
            ){
        String url = bucketService.uploadFile(file, bucketName);

        Optional<Car> byId = carRepository.findById(carId);
        CarImage image = null;
        if(byId.isPresent()){
            Car car = byId.get();
            image = new CarImage();
            image.setUrl(url);
            image.setCar(car);
            imageRepository.save(image);
        }
        else{
            throw new NullPointerException("Car is not present in database with the given id");
        }
       return  new ResponseEntity<>(image ,HttpStatus.CREATED );
    }

    // based on car id i should get all the images from the database

    // http://localhost:8080/api/v1/car/get-allCarImages
    @GetMapping(ApiEndpoints.GET_ALL_URL)
    public List<String> getAllCarImages(@RequestParam Long carId){
        return imageRepository.findByCarId(carId);
    }


}
