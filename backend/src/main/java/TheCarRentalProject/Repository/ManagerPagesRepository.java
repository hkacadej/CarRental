package TheCarRentalProject.Repository;

import TheCarRentalProject.users.ManagerPages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ManagerPagesRepository extends JpaRepository<ManagerPages,Integer> {
    @Query("SELECT m.pageName FROM ManagerPages m WHERE m.role LIKE %:role%")

    List<String> findPageNameByRole(String role);
}
