package TheCarRentalProject.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "manager_pages")
@Data
public class ManagerPages {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    private Integer id;

    @Column(name = "pageName")
    private String pageName;

    @Column
    private String role;

}
