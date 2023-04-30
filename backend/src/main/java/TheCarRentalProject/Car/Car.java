package TheCarRentalProject.Car;

import TheCarRentalProject.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.Set;

@Entity
@Data
@Table(name="car")
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "plate")
    private String plate;

    @Column(name = "MAKE")
    private String make;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "FUEL")
    private String fuel;

    @Column(name = "ENGINE")
    private String engine;

    @Column(name = "YEAR")
    private Year year;

    @Column(name = "CAPACITY")
    private Integer capacity;

    @Column(name = "price")
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "STATUS")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_category"))
    private CarCategory category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private Set<Reservation> reservations;

}
