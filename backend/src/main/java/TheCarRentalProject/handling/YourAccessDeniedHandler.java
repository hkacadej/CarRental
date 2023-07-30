package TheCarRentalProject.handling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class YourAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException, IOException {
        // Handle access-denied exceptions, e.g., when a user does not have sufficient permissions to access a resource.
        // In this example, we set the HTTP status code to 403 (Forbidden)
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied: " + accessDeniedException.getMessage());
    }
}