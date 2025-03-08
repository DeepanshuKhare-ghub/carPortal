package in.springsecurity.entity.evaluation;

import jakarta.persistence.*;

@Entity
public class ActualCarDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  int kms;

    private int yearOfManufacturing;

    private  double MeasurementOfTire;

    private boolean stephne;

    private String fuelType;

    private String TransmissionType;

    private String brand;

    private String model;

    @ManyToOne
    @JoinColumn(name = "car_detailed_evaluation_id")
    private CarEvaluationPhotos carEvaluationPhotos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKms() {
        return kms;
    }

    public void setKms(int kms) {
        this.kms = kms;
    }

    public int getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public void setYearOfManufacturing(int yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    public double getMeasurementOfTire() {
        return MeasurementOfTire;
    }

    public void setMeasurementOfTire(double measurementOfTire) {
        MeasurementOfTire = measurementOfTire;
    }

    public boolean isStephne() {
        return stephne;
    }

    public void setStephne(boolean stephne) {
        this.stephne = stephne;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return TransmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        TransmissionType = transmissionType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarEvaluationPhotos getCarEvaluationPhotos() {
        return carEvaluationPhotos;
    }

    public void setCarEvaluationPhotos(CarEvaluationPhotos carEvaluationPhotos) {
        this.carEvaluationPhotos = carEvaluationPhotos;
    }
}
