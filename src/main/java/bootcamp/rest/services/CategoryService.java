package bootcamp.rest.services;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bootcamp.rest.models.entities.Category;
import bootcamp.rest.models.repos.CategoryRepo;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepo categoryRepo;

    public Category create(Category category){
        return categoryRepo.save(category);
    }

    public Category findOneCategory(Long Id){
        Optional<Category> category = categoryRepo.findById(Id);

        if(!category.isPresent()){
            throw new RuntimeException("Category not found for this id :: " + Id); 
        }

        return category.get();
    }

    public Iterable<Category> findAllCategory(){
        return categoryRepo.findAll();
    }

    public void removeOneCategory(Long Id){
        Optional<Category> category = categoryRepo.findById(Id);
        if(!category.isPresent()){
            throw new RuntimeException("Category not found for this id :: " + Id); 
        }
        categoryRepo.deleteById(Id);
    }

    //Call the Custom Method to Find Category by Name
    public List<Category> findByNameCategoryTitle(String title){
        return categoryRepo.findByName(title);
    }
}
