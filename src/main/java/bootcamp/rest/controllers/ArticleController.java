package bootcamp.rest.controllers;

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
import bootcamp.rest.services.ArticleService;
import jakarta.validation.Valid;
import bootcamp.rest.models.entities.Article;
import bootcamp.rest.dto.ResponData;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    public ArticleService articleService;
    
    //This article will send through by Request Body Client (Web or Mobile) 
    @PostMapping
    public ResponseEntity<ResponData<Article>> create(@Valid @RequestBody Article article, Errors errors){

        ResponData<Article> responData = new ResponData<>();

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
    public Iterable<Article> findAll(){
        return articleService.findAllArticle();
    }

    @GetMapping("/{id}")
    public Article findOneArticle(@PathVariable("id") Long Id) throws Exception{

        return articleService.findOneArticle(Id); 
    }

    @PutMapping
    public ResponseEntity<ResponData<Article>> update(@Valid @RequestBody Article article, Errors errors){
        ResponData<Article> responData = new ResponData<>();

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
    public void deleteOne(@PathVariable("id") Long Id) throws Exception{
        articleService.removeOneArticle(Id);
    }
}
