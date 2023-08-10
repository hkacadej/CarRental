package TheCarRentalProject.users;

import java.util.List;

public class UserDto {
    private String userName;
    private List<String> roles;

    public UserDto(String userName, List<String> roles) {
        this.userName = userName;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
