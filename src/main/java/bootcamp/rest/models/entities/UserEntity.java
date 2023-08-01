package bootcamp.rest.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_user")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;    

    @NotEmpty(message="Name is required")
    @Size(message="Name only max 200 characters", max = 200)
    @Column(nullable=false)
    private String name;

    @NotEmpty(message="Email is required")
    @Size(message="Email only max 50 characters", max = 50)
    @Email(message="Email Format is not valid")
    @Column(unique = true, nullable=false)
    private String email;

    @NotEmpty(message="Password is required")
    @Column(nullable=false)
    private String password;

    @NotEmpty(message="Phone Number is required")
    @Size(message="Phone Number only max 13 digit", max = 13)
    @Column(nullable=false)
    private String phone_number;

    /*
     * 
     */
    @NotEmpty(message="Roles is required")
    @Size(message="Roles only max 30 characters", max = 30)
    @Column(nullable=false)
    private String roles;

    /* 
     * 0 Tidak Terdaftar 
     * 1 Belum Tervalidasi
     * 2 Terdaftar
    */
    @Column(nullable=false, length = 30)
    private int status;
}
