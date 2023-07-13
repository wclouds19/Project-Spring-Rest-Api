package bootcamp.rest.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Entity
@Table(name="tbl_article")
public class Article implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id //To Set As Primary Key
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //To Set Id AutoIncrement
    private Long Id;

    @NotEmpty(message="Title is required")
    @Column(name="title_article", length=30)
    private String title;
    
    @NotEmpty(message="Description is required")
    @Column(name="description", length=50)
    private String description;
        
    @NotEmpty(message="Post Date is required")
    @Column(name="post_date", length=10)
    private String date;

    @ManyToOne
    private Category category;    
    
    @ManyToOne
    private Author author; 

    public Article() {

    }

    public Article(Long id, String title, String description, String date, Category category, Author author) {
        Id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.category = category;
        this.author = author;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {    
        this.date = date;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

}
