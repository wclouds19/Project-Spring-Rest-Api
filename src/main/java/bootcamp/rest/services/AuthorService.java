package bootcamp.rest.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import bootcamp.rest.models.entities.Author;
import bootcamp.rest.models.repos.AuthorRepo;

@Service
@Transactional
public class AuthorService {
    
    @Autowired
    private AuthorRepo authorRepo;

    public Author create(Author author){
        return authorRepo.save(author);
    }

    public Author findOneAuthor(Long Id){
        Optional<Author> author = authorRepo.findById(Id);

        if(!author.isPresent()){
            throw new RuntimeException("Author not found for this id :: " + Id); 
        }

        return author.get();
    }

    public Iterable<Author> findAllAuthor(){
        return authorRepo.findAll();
    }

    public void removeOneAuthor(Long Id){
        Optional<Author> author = authorRepo.findById(Id);
        if(!author.isPresent()){
            throw new RuntimeException("Article not found for this id :: " + Id); 
        }
        authorRepo.deleteById(Id);
    }

    //This is Derived Query Methods to Find the Author by Email
    public Author findByEmail(String email){
        return authorRepo.findByEmail(email);
    }

    //This is Derived Query Methods to Find the Author by Name
    public List<Author> findByName(String name){
        return authorRepo.findByNameContains(name);
    }

    //This is Derived Query Methods to Find the Author by Specific Prefix Name
    public List<Author> findByNameStartWith(String name){
        return authorRepo.findByNameStartingWith(name);
    }

    //This is Derived Query Methods to Find the Author by Name or by Email
    public List<Author> findByNameOrEmail(String name, String email){
        return authorRepo.findByNameContainsOrEmailContains(name, email);
    }
}
