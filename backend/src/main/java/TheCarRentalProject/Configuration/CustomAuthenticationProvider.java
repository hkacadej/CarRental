package TheCarRentalProject.Configuration;

import TheCarRentalProject.Service.implementations.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Primary

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    @Autowired
    public CustomAuthenticationProvider(UserDetailsServiceImpl userDetailsService) {
        setUserDetailsService(userDetailsService);
    }
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));

        }
            // Override this method to disable account locking checks

        //super.additionalAuthenticationChecks(userDetails, authentication);

        // If we reach here, it means the password is correct.
        // If the password is incorrect, the DaoAuthenticationProvider would have thrown an exception before reaching this point.

        // You can add additional checks here if needed.

        // If the credentials are still not authenticated at this point, throw BadCredentialsException
        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Authentication failed: Invalid credentials.");
        }
    }
}





