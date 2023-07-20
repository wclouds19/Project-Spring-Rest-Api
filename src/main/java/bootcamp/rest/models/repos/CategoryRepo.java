package bootcamp.rest.models.repos;

import bootcamp.rest.models.entities.Category;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
 
    //This is Custom Method to Find the Category by name
    List<Category> findByName(String name);
}
