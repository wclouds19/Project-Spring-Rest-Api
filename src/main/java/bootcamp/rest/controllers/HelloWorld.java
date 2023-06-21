package bootcamp.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/greetings") //TO ACCESS BY URL
public class HelloWorld {

    @GetMapping
    public String greetings(){
        return "Hello World!";
    }
/*    
    @PostMapping
    public String othersGreetings(){
        return "Hello Ha!";
    }
*/    
}
