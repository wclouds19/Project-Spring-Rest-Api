package bootcamp.rest.controllers;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import bootcamp.rest.dto.CategoryDto;
import bootcamp.rest.dto.ResponDataDto;
import bootcamp.rest.dto.SearchDataDto;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public ModelMapper modelMapper;
    
    //This Category will send through by Request Body Client (Web or Mobile) 
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponDataDto<Category>> create(@Valid @RequestBody CategoryDto categoryDataTransferObject, Errors errors){

        ResponDataDto<Category> responData = new ResponDataDto<>();

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
        responData.setdata(categoryService.create(category));
        return ResponseEntity.ok(responData);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Iterable<Category> findAll(){
        return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Category findOneCategory(@PathVariable("id") Long Id){
        return categoryService.findOneCategory(Id); 
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponDataDto<Category>> update(@Valid @RequestBody CategoryDto categoryDataTransferObject, Errors errors){

        ResponDataDto<Category> responData = new ResponDataDto<>();

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
        responData.setdata(categoryService.create(category));
        return ResponseEntity.ok(responData);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteOne(@PathVariable("id") Long Id){
        categoryService.removeOneCategory(Id);
    }

    @PostMapping("/search/{size}/{page}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Iterable<Category> findByName(@RequestBody SearchDataDto searchData, @PathVariable("size") int size, @PathVariable("page") int page){

        Pageable pageable = PageRequest.of(page, size);
        return categoryService.findByName(searchData.getNameKey(), pageable);
    } 

    @PostMapping("/search/{size}/{page}/{sort}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Iterable<Category> findByName(@RequestBody SearchDataDto searchData, @PathVariable("size") int size, @PathVariable("page") int page, @PathVariable("sort") String sort){

        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));  // Default Asc

        if(sort.equalsIgnoreCase("desc")){
            pageable = PageRequest.of(page, size, Sort.by("id").descending()); // If Input Default Desc
        }

        return categoryService.findByName(searchData.getNameKey(), pageable);
    } 

    //This Method to Create More Category Data
    @PostMapping("/createmoredata")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponDataDto<Iterable<Category>>> createMoreData(@RequestBody Category[] category){

        ResponDataDto<Iterable<Category>> responData = new ResponDataDto<>();
        responData.setStatus(true);
        responData.setdata(categoryService.createMoreData(Arrays.asList(category)));
        return ResponseEntity.ok(responData);
    }
}
