package in.springsecurity.entity.cars;

import jakarta.persistence.*;

@Entity
@Table(name = "car_brand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="brand", nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return name;
    }

    public void setBrand(String name) {
        this.name = name;
    }
}