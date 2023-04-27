package TheCarRentalProject.Car;

import TheCarRentalProject.Enum.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="CarRentalProject")
@NoArgsConstructor
public class Car {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    private Long Id;

    private String Plate;
    private String make;
    private String model;
    private String fuel;
    private Integer engine;
    private  Integer year;
    private Integer capacity;
    private Integer price;
    private String imageUrl;
    private Status status;

    public void setId(Long id) {
        Id = id;
    }

    public String getPlate() {
        return Plate;
    }

    public void setPlate(String plate) {
        Plate = plate;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Integer getEngine() {
        return engine;
    }

    public void setEngine(Integer engine) {
        this.engine = engine;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Car(String plate, String make, String model, String fuel, Integer engine, Integer year, Integer capacity, Integer price, String imageUrl) {
        Plate = plate;
        this.make = make;
        this.model = model;
        this.fuel = fuel;
        this.engine = engine;
        this.year = year;
        this.capacity = capacity;
        this.price = price;
        this.imageUrl = imageUrl;

    }
    @Override
    public String toString() {
        return "Car{" +
                "Id=" + Id +
                ", Plate='" + Plate + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", fuel='" + fuel + '\'' +
                ", engine=" + engine +
                ", year=" + year +
                ", capacity=" + capacity +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
