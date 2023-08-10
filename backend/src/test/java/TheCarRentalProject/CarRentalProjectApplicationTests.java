package TheCarRentalProject;

import TheCarRentalProject.Service.CarService;
import TheCarRentalProject.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarRentalProjectApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	CarService carService;
	@Test
	void contextLoads() {
	}

	@Test
	void testGetRoles() {
		// Given
		// Assuming that the getRoles() method in UserService returns the roles as an array of strings
		String[] roles = {"admin", "user"};

		// When
		String actualRoles = userService.toRoleArray(roles);
		String expecdedRoles =  "ROLE_ADMIN,ROLE_USER";

		// Then
		// Compare the expected and actual roles
		assertEquals(expecdedRoles,actualRoles );
	}

	@Test
	//2023-04-30 - 2023-05-30
	void testDateOverlapingSame() throws Exception{
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse("2023-04-30");
		Date to = new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-30");
		boolean overlap = carService.reservationOverlaps(from,to);
		assertFalse(overlap);
	}

	@Test
	void testDateOverlapingNoOL() throws Exception{
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse("2023-06-30");
		Date to = new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-30");
		boolean overlap = carService.reservationOverlaps(from,to);
		assertTrue(overlap);
	}

	@Test
	void testDateOverlapingY1() throws Exception{
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse("2023-03-30");
		Date to = new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-01");
		boolean overlap = carService.reservationOverlaps(from,to);
		assertFalse(overlap);
	}

	@Test
	void testDateOverlapingY2() throws Exception{
		Date from = new SimpleDateFormat("yyyy-MM-dd").parse("2023-05-25");
		Date to = new SimpleDateFormat("yyyy-MM-dd").parse("2023-06-01");
		boolean overlap = carService.reservationOverlaps(from,to);
		assertFalse(overlap);
	}
}
