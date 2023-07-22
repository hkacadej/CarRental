package TheCarRentalProject.Service;


import TheCarRentalProject.Repository.UserRepository;
import TheCarRentalProject.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(String username, String password) {
        // Encode the password before saving it to the database
        String encodedPassword = passwordEncoder.encode(password);

        // Create a new user object
        User user = new User(username, encodedPassword);

        // Save the user to the database
        userRepository.save(user);
    }
}

