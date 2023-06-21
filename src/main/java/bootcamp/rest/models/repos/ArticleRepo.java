package bootcamp.rest.models.repos;

import java.util.List;
import bootcamp.rest.models.entities.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepo extends CrudRepository<Article, Long>{

    //This is Custom Method to Find the Article Title by name
    List<Article> findByTitle(String title);
    
}
