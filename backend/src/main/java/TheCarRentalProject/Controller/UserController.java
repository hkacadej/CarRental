package TheCarRentalProject.Controller;

import TheCarRentalProject.Service.UserService;
import TheCarRentalProject.jwt.AuthRequest;
import TheCarRentalProject.jwt.AuthResponse;
import TheCarRentalProject.users.UserDto;
import TheCarRentalProject.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired private JwtUtil jwtUtil;
    @Autowired AuthenticationManager authManager;
    @Autowired UserService userService;
    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            System.out.println("erdi requesti");
                Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword())
                );
            System.out.println(authentication);
            User user = (User) authentication.getPrincipal();
            System.out.print(user.getUsername());
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getUsername(), accessToken);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            System.out.println("jari");
            // Authentication failed due to incorrect credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(path = "/roles")
    public ResponseEntity<?> getRoles(){
        return ResponseEntity.ok(userService.getRoles());
    }

    @PostMapping(path = "/cars/insert/user")
    public ResponseEntity<?> saveUser(@RequestBody TheCarRentalProject.users.User user){
        try {
            logger.info(user.toString());
            return ResponseEntity.ok(userService.saveUser(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
    @GetMapping("/manage-user/list")
    public List<UserDto> getUsers(){
        return userService.getAllUsers();
    }

}

