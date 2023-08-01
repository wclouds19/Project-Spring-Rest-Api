package bootcamp.rest.services;

import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import bootcamp.rest.models.entities.Category;
import bootcamp.rest.models.repos.CategoryRepo;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepo categoryRepo;

    public Category create(Category category){
        
        //If Update Process
        if(category.getId() != null){
            Category currentCategory = categoryRepo.findById(category.getId()).get();
            currentCategory.setName(category.getName());
            currentCategory.setDescription(category.getDescription());
            category = currentCategory;
        }

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

    //Call the Custom Method to Find Category by Name do Paging and Sorting
    public Iterable<Category> findByName(String name, Pageable pageable){
        return categoryRepo.findByNameContains(name, pageable);
    }

    //Custom Method to Create a more Data Category
    public Iterable<Category> createMoreData(Iterable <Category> category){
        return categoryRepo.saveAll(category);
    }
}
