package TheCarRentalProject.jwt;

public class AuthResponse {
    private String username;
    private String accessToken;

    public AuthResponse() { }

    public AuthResponse(String email, String accessToken) {
        this.accessToken = accessToken;
        this.username = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}