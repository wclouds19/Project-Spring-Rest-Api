package bootcamp.rest.models.repos;

import bootcamp.rest.models.entities.Author;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepo extends CrudRepository<Author, Long> {
    
     //This is Custom Method to Find the Author by name
    List<Author> findByName(String name);
}
