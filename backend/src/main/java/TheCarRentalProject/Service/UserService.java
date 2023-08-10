package TheCarRentalProject.Service;


import TheCarRentalProject.Enum.Role;
import TheCarRentalProject.Repository.UserRepository;
import TheCarRentalProject.users.User;
import TheCarRentalProject.users.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String toRoleArray(String[] roles){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < roles.length ; i++ ) {
            stringBuilder.append("ROLE_").append(roles[i].toUpperCase());

            if(i < roles.length - 1){
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }

    public User saveUser(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String[] userRoles = user.getRoles().split(",");
        return this.userRepository.save(user);
    }
    public List<UserDto> getAllUsers() {

        List<UserDto> userDtos = userRepository.findAll().stream()
                .map(user -> {
                    return new UserDto(user.getUsername(), Arrays.asList(user.getRoles().split(",")));
                }).toList();
        return userDtos;
    }

    public List<Role> getRoles(){
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ADMIN);
        roles.add(Role.USER);
        return roles;
    }
}

