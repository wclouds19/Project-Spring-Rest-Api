package bootcamp.rest.controllers;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import bootcamp.rest.dto.AuthorDto;
import bootcamp.rest.dto.ResponDataDto;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponDataDto<Author>> create(@Valid @RequestBody AuthorDto authorDataTransferObject, Errors errors){

        ResponDataDto<Author> responData = new ResponDataDto<>();

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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Iterable<Author> findAll(){
        return authorService.findAllAuthor();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Author findOneAuthor(@PathVariable("id") Long Id){
        return authorService.findOneAuthor(Id); 
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponDataDto<Author>> update(@Valid @RequestBody AuthorDto authorDataTransferObject, Errors errors){

        ResponDataDto<Author> responData = new ResponDataDto<>();

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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteOne(@PathVariable("id") Long Id){
        authorService.removeOneAuthor(Id);
    }

    @PostMapping("/search/email")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Author findAuthorByEmail(@RequestBody SearchDataDto searchData){
        return authorService.findByEmail(searchData.getEmailKey());
    }

    @PostMapping("/search/name")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Author> findAuthorByName(@RequestBody SearchDataDto searchData){
        return authorService.findByName(searchData.getNameKey());
    }

    @PostMapping("/search/namestartwith")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Author> findAuthorByNameStartWith(@RequestBody SearchDataDto searchData){
        return authorService.findByNameStartWith(searchData.getNameKey());
    }

    @PostMapping("/search/nameoremail")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Author> findAuthorByNameOrEmail(@RequestBody SearchDataDto searchData){
        return authorService.findByNameOrEmail(searchData.getNameKey(), searchData.getEmailKey());
    }
}