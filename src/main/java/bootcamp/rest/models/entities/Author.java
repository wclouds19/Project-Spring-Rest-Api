package bootcamp.rest.models.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbl_author")
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
 
    @Id //To Set As Primary Key
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //To Set Id AutoIncrement
    private Long Id;

    @Column(name="author_name", length=50, nullable=false)
    private String name;

    @Column(name="email", length=50, nullable=false, unique=true)
    private String email;

    @Column(name="address", length=225, nullable=false)
    private String address;

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }  

    public Long getId() {
        return Id;
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
