package in.springsecurity.repository.cars;

import in.springsecurity.entity.cars.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<CarBrand, Long> {

    @Query("SELECT b from CarBrand b where b.name =:brandName")
    Optional<CarBrand> findByBrandName(@Param("brandName") String brandName);
}
