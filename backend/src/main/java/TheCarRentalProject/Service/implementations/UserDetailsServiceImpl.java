package TheCarRentalProject.Service.implementations;

import TheCarRentalProject.Repository.UserRepository;
import TheCarRentalProject.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
            // Create a UserDetails object using the user data fetched from the repository
        // For simplicity, we assume the roles are stored as a comma-separated string in the database
        if (user == null){
           throw new UsernameNotFoundException("User not found with username: " + username);
        }

        String[] roles = user.getRoles().split(",");

        List<GrantedAuthority> authorities = Arrays
                .stream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        logger.info(authorities.toString());
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}