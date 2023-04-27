package TheCarRentalProject.Configuration;

import TheCarRentalProject.Car.Car;
import TheCarRentalProject.Repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

@org.springframework.context.annotation.Configuration
public class CarConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(CarRepository repository){
        return args -> {

           /* for (int i = 0 ; i<5 ; i++){
                Car volkswagenGolf = new Car("AA111AA", "Volkswagen", "Golf 7", "diesel", 1600, 2015, 5, 40, "assets/images/cars/golfmk7.png");
                Car mercedesC = new Car("AA222AA", "Mercedes-Benz", "W203-C-Class", "diesel", 2200, 2010, 5, 39, "assets/images/cars/benz.png");
                repository.saveAll(List.of(volkswagenGolf, mercedesC));
            }*/

        };
    }
}
