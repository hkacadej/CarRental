package TheCarRentalProject.Service;

import TheCarRentalProject.Model.Car;
import TheCarRentalProject.Model.CarCategory;
import TheCarRentalProject.Model.Reservation;
import TheCarRentalProject.Repository.CarCategoryRepository;
import TheCarRentalProject.Repository.CarRepository;
import TheCarRentalProject.Repository.ReservationRepository;
import TheCarRentalProject.response.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service

public class CarService {
    private final CarRepository carRepository;
    private final CarCategoryRepository carCategoryRepository;
    private final ReservationRepository reservationRepository;

@Autowired
    public CarService(CarRepository carRepository, CarCategoryRepository carCategoryRepository , ReservationRepository reservationRepository) {
        this.carRepository= carRepository;
        this.carCategoryRepository = carCategoryRepository;
        this.reservationRepository = reservationRepository;
}

    public List<Car> getCars() {
    return carRepository.findAll();
    }
    public List<Car> getCarsByCategory(Long id) {

    return carRepository.findByCategoryId(id);
    }
    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }

    public List<CarCategory> getCategories(){
    return carCategoryRepository.findAll();
    }

    public List<Car> searchCars(String keyword){
        return carRepository.findByMakeContaining(keyword);
    }

    public List<Reservation> getCarReservation (Long id){
        return reservationRepository.findAllByCarId(id);
    }

    public Response saveReservation(Reservation reservation) {
        System.out.println(reservation);
        Long carId = reservation.getCar().getId();
        List<Reservation> carReservations = this.getCarReservation(carId);
        Date dateFrom = reservation.getDateFrom();
        Date dateTo = reservation.getDateTo();
        Boolean reservationsOverlap = false;
        for(Reservation existingReservation : carReservations) {
            Date dateTo1 = existingReservation.getDateTo();
            Date dateFrom1 = existingReservation.getDateFrom();
            reservationsOverlap = (dateFrom1.before(dateTo) && dateTo1.after(dateFrom));
        }
        if(!reservationsOverlap){
            reservationRepository.save(reservation);
            return new Response( 200, "Reservation saved successfully.");
        }else {
            return new Response(400, "Reservation overlaps with an existing reservation.");
            //it showld return a response with a message
        }
    }
}
