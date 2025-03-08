package in.springsecurity.repository.evaluation;

import in.springsecurity.entity.evaluation.CarEvaluationPhotos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEvaluationPhotosRepository extends JpaRepository<CarEvaluationPhotos, Long> {
}