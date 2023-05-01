package TheCarRentalProject.Repository;

import TheCarRentalProject.Model.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarCategoryRepository extends JpaRepository<CarCategory , Long> {
    List<CarCategory> findAll();
}
