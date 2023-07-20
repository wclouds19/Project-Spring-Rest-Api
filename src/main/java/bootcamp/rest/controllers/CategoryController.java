package bootcamp.rest.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bootcamp.rest.services.CategoryService;
import jakarta.validation.Valid;
import bootcamp.rest.models.entities.Category;
import bootcamp.rest.dto.CategoryDataTransferObject;
import bootcamp.rest.dto.ResponData;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    public CategoryService CategoryService;

    @Autowired
    public ModelMapper modelMapper;
    
    //This Category will send through by Request Body Client (Web or Mobile) 
    @PostMapping
    public ResponseEntity<ResponData<Category>> create(@Valid @RequestBody CategoryDataTransferObject categoryDataTransferObject, Errors errors){

        ResponData<Category> responData = new ResponData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responData.getMessages().add(error.getDefaultMessage());
            }
            responData.setStatus(false);
            responData.setdata(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }

        Category category = modelMapper.map(categoryDataTransferObject, Category.class);

        responData.setStatus(true);
        responData.setdata(CategoryService.create(category));
        return ResponseEntity.ok(responData);
    }

    @GetMapping
    public Iterable<Category> findAll(){
        return CategoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    public Category findOneCategory(@PathVariable("id") Long Id){

        return CategoryService.findOneCategory(Id); 
    }

    @PutMapping
    public ResponseEntity<ResponData<Category>> update(@Valid @RequestBody CategoryDataTransferObject categoryDataTransferObject, Errors errors){

        ResponData<Category> responData = new ResponData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responData.getMessages().add(error.getDefaultMessage());
            }
            responData.setStatus(false);
            responData.setdata(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }

        Category category = modelMapper.map(categoryDataTransferObject, Category.class); //Set Category Data using Map

        responData.setStatus(true);
        responData.setdata(CategoryService.create(category));
        return ResponseEntity.ok(responData);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long Id){
        CategoryService.removeOneCategory(Id);
    }
}
