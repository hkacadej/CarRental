package TheCarRentalProject.jwt;

import TheCarRentalProject.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired AuthenticationManager authManager;
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

}

