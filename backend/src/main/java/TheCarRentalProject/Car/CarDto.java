package TheCarRentalProject.Car;

import org.springframework.web.multipart.MultipartFile;

public class CarDto {
    private Car car;
    private MultipartFile image;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
