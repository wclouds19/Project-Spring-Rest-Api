package bootcamp.rest.models.repos;

import bootcamp.rest.models.entities.Author;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepo extends CrudRepository<Author, Long> {
    
    //This is Derived Query Methods to Find the Author by Email
    Author findByEmail(String email);  //return only one email

    //This is Derived Query Methods to Find the Author by Name
    //OrderByIdDesc after Function Name (Optional)
    List<Author> findByNameContains(String name);  //LIKE in SQL Query

    //This is Derived Query Methods to Find the Author by Name by Specific Prefix Name
    List<Author> findByNameStartingWith(String prefixName);  //LIKE in SQL Query specific prefix

    //This is Derived Query Methods to Find the Author by Name or By Email
    List<Author> findByNameContainsOrEmailContains(String name, String email);  //LIKE in SQL Query Or Condition
}
