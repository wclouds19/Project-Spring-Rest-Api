package bootcamp.rest.services;

import bootcamp.rest.models.entities.Article;
import bootcamp.rest.models.repos.ArticleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService {
    @Autowired
    private ArticleRepo articleRepo; 

    //This Method Run Can Be Create & Update
    public Article create(Article article){
        return articleRepo.save(article);
    }

    public Article findOneArticle(Long Id) throws Exception{

        Optional<Article> article = articleRepo.findById(Id);
        if(!article.isPresent()){
            return article.orElseThrow(() -> new Exception("Article not found for this id :: " + Id));
        }
        return article.get(); 
    }

    public Iterable<Article> findAllArticle(){
        return articleRepo.findAll();
    }

    public void removeOneArticle(Long Id) throws Exception{
        Optional<Article> article = articleRepo.findById(Id);
        if(!article.isPresent()){
            article.orElseThrow(() -> new Exception("Article not found for this id :: " + Id));
        }
        articleRepo.deleteById(Id);
    }

    //Call the Custom Method Article Title to Find Article Title by Name
    public List<Article> findByNameArticleTitle(String title){
        return articleRepo.findByTitle(title);
    }
}
