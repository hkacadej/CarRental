package TheCarRentalProject.Repository;

import TheCarRentalProject.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository


public interface CarRepository extends JpaRepository<Car, Long> {
}
