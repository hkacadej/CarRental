package TheCarRentalProject.Controller;

import TheCarRentalProject.Car.Car;
import TheCarRentalProject.Service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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


}