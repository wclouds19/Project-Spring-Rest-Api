package bootcamp.rest.models.repos;

import bootcamp.rest.models.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long> {
 
    //This is Query Derived Method to Find the Category by name do Paging and Sorting
    Page<Category> findByNameContains(String name, Pageable pageable);
}
