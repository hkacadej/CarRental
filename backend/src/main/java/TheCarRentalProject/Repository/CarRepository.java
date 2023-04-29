package TheCarRentalProject.Repository;

import TheCarRentalProject.Car.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@org.springframework.stereotype.Repository


public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByCategoryId(@Param("id") Long id);
    List<Car> findByMakeContaining(@Param("make") String make);
}
