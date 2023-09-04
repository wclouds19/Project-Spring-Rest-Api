package bootcamp.rest.controllers;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bootcamp.rest.services.ArticleService;
import jakarta.validation.Valid;
import bootcamp.rest.models.entities.Article;
import bootcamp.rest.dto.ResponDataDto;
import bootcamp.rest.dto.SearchDataDto;
import bootcamp.rest.helpers.CsvHelpers;

import java.util.List;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    public ArticleService articleService;  
  
    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponDataDto<Article>> create(@Valid @RequestBody Article article, Errors errors){

        ResponDataDto<Article> responData = new ResponDataDto<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responData.getMessages().add(error.getDefaultMessage());
            }
            responData.setStatus(false);
            responData.setdata(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }

        responData.setStatus(true);
        responData.setdata(articleService.create(article));
        return ResponseEntity.ok(responData);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Iterable<Article> findAll(){
        return articleService.findAllArticle();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Article findOneArticle(@PathVariable("id") Long Id){

        return articleService.findOneArticle(Id); 
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<ResponDataDto<Article>> update(@Valid @RequestBody Article article, Errors errors){
        
        ResponDataDto<Article> responData = new ResponDataDto<>();

        if(errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responData.getMessages().add(error.getDefaultMessage());
            }
            responData.setStatus(false);
            responData.setdata(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }

        responData.setStatus(true);
        responData.setdata(articleService.create(article));
        return ResponseEntity.ok(responData);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void deleteOne(@PathVariable("id") Long Id){
        articleService.removeOneArticle(Id);
    }

    @PostMapping("/search/title")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Article> findArticleByTitle(@RequestBody SearchDataDto searchKey){
        return articleService.findByTitle(searchKey.getSearchKey());
    }

    @GetMapping("/search/category/{id}")
    public List<Article> findArticleByCategory(@PathVariable("id") Long Id){
        return articleService.findByCategory(Id);
    }

    @GetMapping("/search/author/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public List<Article> findArticleByAuthor(@PathVariable("id") Long Id){
        return articleService.findByAuthor(Id);
    }

    @PostMapping("/upload_csv")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> uploadFileCsv(@RequestParam MultipartFile file){

        ResponDataDto<Article> responData = new ResponDataDto<>();

        if(CsvHelpers.hasCsvFormat(file)){
            responData.setStatus(false);
            responData.getMessages().add("Please upload CSV File");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responData);
        }

        try{
            List<Article> articles = articleService.uploadCsv(file);
            responData.setStatus(true);  
            responData.getMessages().add("Upload the file successfully : "+ file.getOriginalFilename());
            responData.setdata(articles);
            return ResponseEntity.ok(responData);
        }catch(Exception e){
            responData.setStatus(false);
            responData.getMessages().add("Could Not Upload the File: "+ file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responData);
        }
    }
}