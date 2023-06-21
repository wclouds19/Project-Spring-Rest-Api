package bootcamp.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bootcamp.rest.services.ArticleService;
import bootcamp.rest.models.entities.Article;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    public ArticleService articleService;
    
    //This article will send through by Request Body Client (Web or Mobile) 
    @PostMapping
    public Article create(@RequestBody Article article){
        return articleService.create(article);
    }

    @GetMapping
    public Iterable<Article> findAll(){
        return articleService.findAllArticle();
    }

    @GetMapping("/{id}")
    public Article findOneArticle(@PathVariable("id") Long Id){
        return articleService.findOneArticle(Id); 

    }

    @PutMapping
    public Article update(@RequestBody Article article){
        return articleService.create(article);
    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") Long Id){
        articleService.removeOneArticle(Id);
    }
}
