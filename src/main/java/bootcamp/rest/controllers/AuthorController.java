package bootcamp.rest.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import bootcamp.rest.dto.AuthorDataTransferObject;
import bootcamp.rest.dto.ResponData;
import bootcamp.rest.dto.SearchDataDto;
import bootcamp.rest.services.AuthorService;
import jakarta.validation.Valid;
import bootcamp.rest.models.entities.Author;

@RestController
@RequestMapping("api/author")
public class AuthorController {

    @Autowired
    public AuthorService authorService;

    @Autowired
    public ModelMapper modelMapper;

    //This Author will send through by Request Body Client (Web or Mobile) 
    @PostMapping
    public ResponseEntity<ResponData<Author>> create(@Valid @RequestBody AuthorDataTransferObject authorDataTransferObject, Errors errors){

        ResponData<Author> responData = new ResponData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responData.getMessages().add(error.getDefaultMessage());
            }
            responData.setStatus(false);
            responData.setdata(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }

        Author authoEntity = modelMapper.map(authorDataTransferObject, Author.class);

        responData.setStatus(true);
        responData.setdata(authorService.create(authoEntity));
        return ResponseEntity.ok(responData);
    }

    @GetMapping
    public Iterable<Author> findAll(){
        return authorService.findAllAuthor();
    }

    @GetMapping("/{id}")
    public Author findOneAuthor(@PathVariable("id") Long Id){
        return authorService.findOneAuthor(Id); 
    }

    @PutMapping
    public ResponseEntity<ResponData<Author>> update(@Valid @RequestBody AuthorDataTransferObject authorDataTransferObject, Errors errors){

        ResponData<Author> responData = new ResponData<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responData.getMessages().add(error.getDefaultMessage());
            }
            responData.setStatus(false);
            responData.setdata(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }

        Author authoEntity = modelMapper.map(authorDataTransferObject, Author.class);

        responData.setStatus(true);
        responData.setdata(authorService.create(authoEntity));
        return ResponseEntity.ok(responData);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long Id){
        authorService.removeOneAuthor(Id);
    }

    @PostMapping("/search/email")
    public Author findAuthorByEmail(@RequestBody SearchDataDto searchData){
        return authorService.findByEmail(searchData.getEmailKey());
    }

    @PostMapping("/search/name")
    public List<Author> findAuthorByName(@RequestBody SearchDataDto searchData){
        return authorService.findByName(searchData.getNameKey());
    }

    @PostMapping("/search/namestartwith")
    public List<Author> findAuthorByNameStartWith(@RequestBody SearchDataDto searchData){
        return authorService.findByNameStartWith(searchData.getNameKey());
    }

    @PostMapping("/search/nameoremail")
    public List<Author> findAuthorByNameOrEmail(@RequestBody SearchDataDto searchData){
        return authorService.findByNameOrEmail(searchData.getNameKey(), searchData.getEmailKey());
    }

}
