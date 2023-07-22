package TheCarRentalProject.jwt;

import org.jetbrains.annotations.NotNull;

public class AuthRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NotNull String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
