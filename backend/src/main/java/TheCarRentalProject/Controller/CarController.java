package TheCarRentalProject.Controller;

import TheCarRentalProject.Car.Car;
import TheCarRentalProject.Car.CarCategory;
import TheCarRentalProject.Car.Reservation;
import TheCarRentalProject.Service.CarService;
import TheCarRentalProject.jwt.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

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

    @PostMapping("upload/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Long id) {
        try{
            String response = carService.saveImage(image,id);
            logger.info(response);
            return ResponseEntity.ok(new AuthResponse(response,"ahahahahh"));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @PostMapping(path = "/cars/insert/car")
    public ResponseEntity<?> saveCar(@RequestBody Car car){
        try {
            logger.info(car.toString());
            //carService.saveImage(car.getImage(),car.getCar().getImageUrl());
            return ResponseEntity.ok(carService.saveCar(car));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @GetMapping(path = "/reservations/{id}")
    public List<Reservation> getCarReservation(@PathVariable Long id){return carService.getCarReservation(id);}
    @PostMapping(path = "/reservations/reserve")
    public boolean saveReservation(@RequestBody Reservation reservation) {
        return carService.saveReservation(reservation);
    }
}