package bootcamp.rest.models.entities;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_category")
@SQLDelete(sql = "UPDATE tbl_category SET isDeleted=true WHERE id=?")   //Soft Delete Implementation
@Where(clause = "isDeleted=false") //Hide Record for Soft Delete
public class Category extends BaseEntity<String> implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id //To Set As Primary Key
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //To Set Id AutoIncrement
    private Long Id;

    @Column(name="category_name", length=30, nullable=false, unique=true)
    private String name;

    @Column(name="category_description", length=50)
    private String description;

    private boolean isDeleted = Boolean.FALSE;
    
    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    } 
}
