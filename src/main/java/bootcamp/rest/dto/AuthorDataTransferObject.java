package bootcamp.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class AuthorDataTransferObject {

    @NotEmpty(message="Name is required")
    private String name;

    @NotEmpty(message="Email is required")
    @Email(message="Email Format is not valid")
    private String email;

    @NotEmpty(message="Address is required")
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
