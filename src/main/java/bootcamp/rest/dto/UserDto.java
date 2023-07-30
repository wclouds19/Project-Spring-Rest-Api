package bootcamp.rest.dto;

import bootcamp.rest.models.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotEmpty(message="Name is required")
    private String name;

    @NotEmpty(message="Email is required")
    @Email(message="Email Format is not valid")
    private String email;

    @NotEmpty(message="Password is required")
    private String password;

    @NotEmpty(message="Phone Number is required")
    @Size(message="Phone Number only max 13 digit", max = 13)
    @Pattern(regexp="(^$|[0-9]{10})", message="Phone Number only number")
    private Long phoneNumber;

    @NotEmpty(message="User Role is required")
    private UserRole userRole;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
}
