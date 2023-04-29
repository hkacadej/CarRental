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
public class CarRentalProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarRentalProjectApplication.class, args);
	}
}
