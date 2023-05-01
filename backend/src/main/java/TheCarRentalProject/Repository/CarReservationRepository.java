package TheCarRentalProject.Repository;

import TheCarRentalProject.Model.CarReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarReservationRepository extends JpaRepository<CarReservation, Long> {
    List<CarReservation> findByCarId(Long carId);
}
