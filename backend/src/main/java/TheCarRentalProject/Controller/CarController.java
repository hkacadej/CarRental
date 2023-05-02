package TheCarRentalProject.Controller;

import TheCarRentalProject.Model.Car;
import TheCarRentalProject.Model.CarCategory;
import TheCarRentalProject.Model.Reservation;
import TheCarRentalProject.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService){
        this.carService= carService;
    }

    @GetMapping(path = "/cars")

    public List<Car>getCars() {
        return carService.getCars();
    }

    @GetMapping(path = "/cars/{id}")
    public Optional<Car> getCarById(@PathVariable Long id){
        return carService.getCarById(id);
    }

    @GetMapping(path = "/cars/category/{id}")
    public List<Car> getCarsByCategory(@PathVariable Long id){
        return carService.getCarsByCategory(id);
    }

    @GetMapping(path = "/categories")
    public List<CarCategory> getCategories(){
        return carService.getCategories();
    }
    @GetMapping(path = "cars/search/{keyword}")
    public List<Car> searchCars(@PathVariable String keyword){
        return carService.searchCars(keyword);
    }

    @GetMapping(name = "/reservations/{carId}")
    public List<Reservation> getCarReservation(@PathVariable Long carId){return carService.getCarReservation(carId);}
}