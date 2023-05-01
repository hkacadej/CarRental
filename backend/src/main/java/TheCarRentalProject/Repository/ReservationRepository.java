package TheCarRentalProject.Repository;

import TheCarRentalProject.Car.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation , Long> {

    @Query("select r from Reservation r where r.car.id = ?1")
    List<Reservation> findAllByCarId(Long id);
}
