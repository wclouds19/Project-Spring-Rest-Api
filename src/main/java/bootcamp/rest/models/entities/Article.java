package bootcamp.rest.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="tbl_article")
public class Article implements Serializable{

    @Id //To Set As Primary Key
    @GeneratedValue(strategy=GenerationType.IDENTITY)  //To Set Id AutoIncrement
    private Long Id;

    @Column(name="author_name", length=100)
    private String author;

    @Column(name="title_article", length=100)
    private String title;
    
    @Column(name="description", length=500)
    private String description;
    
    @Column(name="post_date", length=10)
    private String date;

    public Article() {
    }

    public Article(Long id, String author, String title, String description, String date) {
        Id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
