package TheCarRentalProject.Service;

import TheCarRentalProject.Model.Car;
import TheCarRentalProject.Model.CarCategory;
import TheCarRentalProject.Model.CarReservation;
import TheCarRentalProject.Repository.CarCategoryRepository;
import TheCarRentalProject.Repository.CarRepository;
import TheCarRentalProject.Repository.CarReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service


public class CarService {
    private final CarRepository carRepository;
    private final CarCategoryRepository carCategoryRepository;
    private final CarReservationRepository carReservationRepository;

@Autowired
    public CarService(CarRepository carRepository,
                      CarCategoryRepository carCategoryRepository,
                      CarReservationRepository carReservationRepository) {
        this.carRepository= carRepository;
        this.carCategoryRepository = carCategoryRepository;
        this.carReservationRepository = carReservationRepository;
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

    public List<CarReservation> getCarReservation(Long carId) {
        return carReservationRepository.findByCarId(carId);
    }
}
