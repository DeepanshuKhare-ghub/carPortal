package in.springsecurity.entity.cars;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "car")
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel carModel;


    @ManyToOne
    @JoinColumn(name = "fuelType_id")
    private CarFuelType carFuelType;


    @ManyToOne
    @JoinColumn(name = "year_id")
    private CarYear carYear;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private CarTransmission carTransmission;


    @ManyToOne
    @JoinColumn(name = "brands_id")
    private CarBrand carBrand;



    // get all the image url of a car
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarImage> images;


    //get the car status

    @ManyToOne
    @JoinColumn(name = "status")
    private CarStatus status;

    public List<CarImage> getImages() {
        return images;
    }

    public void setImages(List<CarImage> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarModel getModel() {
        return carModel;
    }

    public void setModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public CarFuelType getFuelType() {
        return carFuelType;
    }

    public void setFuelType(CarFuelType carFuelType) {
        this.carFuelType = carFuelType;
    }

    public CarYear getYear() {
        return carYear;
    }

    public void setYear(CarYear carYear) {
        this.carYear = carYear;
    }

    public CarTransmission getTransmission() {
        return carTransmission;
    }

    public void setTransmission(CarTransmission carTransmission) {
        this.carTransmission = carTransmission;
    }

    public CarBrand getBrand() {
        return carBrand;
    }

    public void setBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }
}