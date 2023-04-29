package TheCarRentalProject.Service;

import TheCarRentalProject.Car.Car;
import TheCarRentalProject.Car.CarCategory;
import TheCarRentalProject.Repository.CarCategoryRepository;
import TheCarRentalProject.Repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service

public class CarService {
    private final CarRepository carRepository;
    private final CarCategoryRepository carCategoryRepository;

@Autowired
    public CarService(CarRepository carRepository, CarCategoryRepository carCategoryRepository) {
        this.carRepository= carRepository;
        this.carCategoryRepository = carCategoryRepository;
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
}
