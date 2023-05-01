package TheCarRentalProject;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@JsonAutoDetect (fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SpringBootApplication
public class CarRentalProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarRentalProjectApplication.class, args);
	}
}
