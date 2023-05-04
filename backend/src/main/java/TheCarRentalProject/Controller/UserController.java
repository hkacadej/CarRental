package TheCarRentalProject.Controller;

import TheCarRentalProject.Service.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
    UserDetailsServiceImpl userDetailsService;
    public UserController(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    @PostMapping("/login")
    @CrossOrigin("http://localhost:4200' ")
    public UserDetails login(@RequestBody UserDetails reservation) {
        return userDetailsService.loadUserByUsername(reservation.getUsername());
    }


    @GetMapping("/logout")
    @CrossOrigin("http://localhost:4200' ")
    public String logout() {
        return "/logout";
    }
}
