package in.springsecurity.entity.evaluation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.springsecurity.entity.cars.Car;
import jakarta.persistence.*;

@Entity
@Table(name = "car_evaluation_photos")
public class CarEvaluationPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonIgnore
    private Car car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}