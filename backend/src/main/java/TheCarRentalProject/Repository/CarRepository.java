package TheCarRentalProject.Repository;

import TheCarRentalProject.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository


public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByCategoryId(@Param("id") Long id);
    List<Car> findByMakeContaining(@Param("make") String make);

    Optional<Car> findById(@Param("id") Long id);

    @Query("SELECT c FROM Car c JOIN FETCH c.category WHERE c.id = :id")
    Car findByIdWithCategory(@Param("id") Long id);
}
