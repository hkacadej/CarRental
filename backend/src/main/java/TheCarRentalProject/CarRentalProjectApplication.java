package TheCarRentalProject;

import TheCarRentalProject.Car.Car;
import TheCarRentalProject.Enum.Status;
import TheCarRentalProject.Repository.CarRepository;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@JsonAutoDetect (fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SpringBootApplication
public class CarRentalProjectApplication implements Runnable{
	private final CarRepository carRepository;

	public CarRentalProjectApplication(CarRepository carRepository) {
		this.carRepository = carRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(CarRentalProjectApplication.class, args);
	}
	@Override
	public void run() {
		for (int i = 0 ; i<5 ; i++){
			Car volkswagenGolf = new Car("AA111AA", "Volkswagen", "Golf 7", "diesel", 1600, 2015, 5, 40, "assets/images/cars/golfmk7.pngsrc\\assets\\images\\cars\\benz.png.jpg");
			Car mercedesC = new Car("AA222AA", "Mercedes-Benz", "W203-C-Class", "diesel", 2200, 2010, 5, 39, "assets/images/cars/benz.png");
			carRepository.saveAll(List.of(volkswagenGolf, mercedesC));
		}

	}

}
