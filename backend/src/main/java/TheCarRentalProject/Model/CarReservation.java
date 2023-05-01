package TheCarRentalProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "reservation")
@NoArgsConstructor
@AllArgsConstructor
public class CarReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_sequence")
    @Column(name = "reservation_id")
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_car"))
    private Car car;

    @Column(name = "start_date")
    private LocalDate reservationStartDate;

    @Column(name = "end_date")
    private LocalDate reservationEndDate;
}
