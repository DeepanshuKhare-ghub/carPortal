package in.springsecurity.entity.cars;

import jakarta.persistence.*;

@Entity
@Table(name = "car_fuelType")
public class CarFuelType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fuelType",nullable = false)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuelType() {
        return type;
    }

    public void setFuelType(String type) {
        this.type = type;
    }
}