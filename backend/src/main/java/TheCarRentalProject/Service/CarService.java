package TheCarRentalProject.Service;

import TheCarRentalProject.Car.Car;
import TheCarRentalProject.Car.CarCategory;
import TheCarRentalProject.Car.Reservation;
import TheCarRentalProject.Repository.CarCategoryRepository;
import TheCarRentalProject.Repository.CarRepository;
import TheCarRentalProject.Repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service

public class CarService {
    private final CarRepository carRepository;
    private final CarCategoryRepository carCategoryRepository;

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);
    private final ReservationRepository reservationRepository;

    @Value("${image.upload.path}") // Injects the property value from application.properties
    private String imageUploadPath;

    @Value("${image.upload.pathFront}") // Injects the property value from application.properties
    private String imageUploadPathFront;

    @Autowired
    public CarService(CarRepository carRepository, CarCategoryRepository carCategoryRepository , ReservationRepository reservationRepository) {
        this.carRepository= carRepository;
        this.carCategoryRepository = carCategoryRepository;
        this.reservationRepository = reservationRepository;
}
    public Car saveCar(Car car){
        car.setImageUrl(generateImageUrl(car.getImageUrl(),car.getCategory().getCategoryName(),car.getPlate()));
        logger.info(car.getImageUrl());
        return this.carRepository.save(car);
    }
    public List<Car> getCars() {
    return carRepository.findAll();
    }
    public List<Car> getCarsByCategory(Long id) {

    return carRepository.findByCategoryId(id);
    }
    public Optional<Car> getCarById(Long id){
        return carRepository.findById(id);
    }

    public List<CarCategory> getCategories(){
    return carCategoryRepository.findAll();
    }

    public List<Car> searchCars(String keyword){
        return carRepository.findByMakeContaining(keyword);
    }

    public List<Reservation> getCarReservation (Long id){
        return reservationRepository.findAllByCarId(id);
    }

    public boolean saveReservation(Reservation reservation) {
        //System.out.println(reservation);
        Long carId = reservation.getCar().getId();
        List<Reservation> carReservations = this.getCarReservation(carId);
        Date dateFrom = reservation.getDateFrom();
        Date dateTo = reservation.getDateTo();
        final boolean[] reservationsOverlap = {true};

        carReservations.stream().forEach(existingReservation -> {
            Date dateTo1 = existingReservation.getDateTo();
            Date dateFrom1 = existingReservation.getDateFrom();
            reservationsOverlap[0] = (dateFrom1.before(dateTo) && dateTo1.after(dateFrom));
        });

        if(!reservationsOverlap[0]){
            System.out.println("Reservation");
            reservationRepository.save(reservation);
            return true;
        }else {
            System.out.println("No Reservation");
            return false;
        }
    }

    public String generateImageUrl(String filename, String category , String plate){
        StringBuilder sb = new StringBuilder();

        logger.info("----------------filenames-----------------------");
        //String imagePath = carRepository.findById(id).get().getImageUrl();
        logger.info(filename.toString());
        sb.append(imageUploadPathFront)
                .append(category.toLowerCase())
                .append("/")
                .append(plate)
                .append("_")
                .append(System.currentTimeMillis())
                .append(".")
                .append(filename.split("/")[1]);
        logger.info(sb.toString());

        return sb.toString();
    };
    public String saveImage(MultipartFile image, Long id){
        //TODO car id , name generation and update name_id_now.getContentType()
        if(image.isEmpty())return "Image empty";
        StringBuilder sb = new StringBuilder();

        logger.info("----------------filenames-----------------------");
        String imageName = carRepository.findById(id).get().getImageUrl();
        logger.info(imageName);

        logger.info("----------------filenames-----------------------");
        logger.info(imageUploadPath + imageName );
        logger.info(imageUploadPathFront + imageName);
        try {

            // Define your directory path and filename here
            File destination = new File(imageUploadPath + imageName);

            image.transferTo(destination);

            return imageUploadPathFront + imageName;
        } catch (IOException e) {
            logger.info(e.getMessage());
            return "Error uploading image: " + e.getMessage();
        }
    }
    public boolean reservationOverlaps (Date dateFrom , Date dateTo ) {

        List<Reservation> carReservations = this.getCarReservation(7L);
        boolean ro = true;

        int count = 0;
        for (Reservation existingReservation : carReservations) {
            Date dateTo1 = existingReservation.getDateTo();
            Date dateFrom1 = existingReservation.getDateFrom();
            if ((dateFrom1.before(dateTo) && dateTo1.after(dateFrom))) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Reservation");
            return true;
        } else {
            System.out.println("No Reservation");
            return false;
        }
    }
}
