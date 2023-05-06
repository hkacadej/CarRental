package TheCarRentalProject.Controller;

import TheCarRentalProject.Car.Car;
import TheCarRentalProject.Car.CarCategory;
import TheCarRentalProject.Car.Reservation;
import TheCarRentalProject.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping(path = "/reservations/{id}")
    public List<Reservation> getCarReservation(@PathVariable Long id){return carService.getCarReservation(id);}

    @PostMapping(path = "/reservations/reserve")
    public boolean saveReservation(@RequestBody Reservation reservation) {
        return carService.saveReservation(reservation);
    }
}