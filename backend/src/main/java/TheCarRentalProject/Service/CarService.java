package TheCarRentalProject.Service;

import TheCarRentalProject.Car.Car;
import TheCarRentalProject.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@org.springframework.stereotype.Service

public class CarService {
    private final CarRepository carRepository;

@Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository= carRepository;    }


    public List<Car> getCars() {
    return carRepository.findAll();
    }
}
