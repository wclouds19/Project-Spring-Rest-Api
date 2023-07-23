package bootcamp.rest.models.repos;

import java.util.List;
import bootcamp.rest.models.entities.Article;
import jakarta.websocket.server.PathParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepo extends CrudRepository<Article, Long>{

    //This is Custom Query Method to Find the Article by Title
    @Query("SELECT a FROM Article a WHERE a.title LIKE :title")
    public List<Article> findByTitle(@PathParam("title") String title);

    //This is Custom Query Method to Find the Article by Category ID
    @Query("SELECT a FROM Article a WHERE a.category.Id = :categoryId")
    public List<Article> findByCategory(@PathParam("categoryId") Long categoryId);

    //This is Custom Query Method to Find the Article by Author ID
    @Query("SELECT a FROM Article a WHERE a.author.Id = :authorId")
    public List<Article> findByAuthor(@PathParam("authorId") Long authorId);
    
}
