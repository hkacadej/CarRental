package TheCarRentalProject.Service;


import TheCarRentalProject.Enum.Role;
import TheCarRentalProject.Repository.ManagerPagesRepository;
import TheCarRentalProject.Repository.UserRepository;
import TheCarRentalProject.users.User;
import TheCarRentalProject.users.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final ManagerPagesRepository managerPagesRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository,
                       ManagerPagesRepository managerPagesRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService) {
        this.managerPagesRepository=managerPagesRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
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

    public ResponseEntity<?> saveUser(User user){
        if(doesUserExist(user.getUsername())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        //String[] userRoles = user.getRoles().split(",");
        user.setPassword(encodedPassword);
        return ResponseEntity.ok(this.userRepository.save(user));
    }
    public List<UserDto> getAllUsers() {

        List<UserDto> userDtos = userRepository.findAll().stream()
                .map(user -> {
                    return new UserDto(user.getUsername(), Arrays.asList(user.getRoles().split(",")));
                }).toList();
        return userDtos;
    }

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        roles.add(Role.ADMIN.getAuthority());
        roles.add(Role.USER.getAuthority());
        return roles;
    }
    public boolean doesUserExist(String username) {
        try {
            userDetailsService.loadUserByUsername(username);
            // If the user exists, this method will return without exceptions
            return true;
        } catch (UsernameNotFoundException ex) {
            // User not found
            return false;
        }
    }
    public List<String> getPages(String[] roles) {
        List<String> pages = new ArrayList<>();

        Arrays.stream(roles).forEach(role ->{
            pages.addAll(managerPagesRepository.findPageNameByRole(role));
        });
        return pages;
    }
}

