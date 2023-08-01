package bootcamp.rest.services;

import bootcamp.rest.models.entities.UserEntity;
import bootcamp.rest.models.repos.UserRepos;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailServices emailService;

    public UserEntity create(UserEntity user) {

        UserEntity userFind = userRepos.findByEmail(user.getEmail()).orElse(null);

        if(userFind != null){
            throw new RuntimeException("Already exist for this email :: " + user.getEmail()); 
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE" + "_" + user.getRoles());
        user.setStatus(0);

        String message = "Welcome to Clouds News" + user.getName() +"!\n\n To activated your process please go to http://cloudsnews.co.id/accounts/confirm \n\n Happy Exercise! \n\n If you did not register any accounts on Clouds News please ignore this message. \n\n Love,\nClouds News Team";

        emailService.sendMail(user.getEmail(),"[Confirm] email address",message);
        
        return userRepos.save(user);
    }

    public UserEntity update(UserEntity user, Long Id) {

        UserEntity userFind = userRepos.findById(Id).orElse(null);

        if(userFind == null){
            throw new RuntimeException("User not found for this id :: " + Id); 
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE" + "_" + user.getRoles());
            
        return userRepos.save(user);       
    }


    public Iterable<UserEntity> findAllUser(){
        return userRepos.findAll();
    }

    public UserEntity findOneUser(Long Id){

        Optional<UserEntity> userFind = userRepos.findById(Id);

        if(!userFind.isPresent()){
            throw new RuntimeException("User not found for this id :: " + Id); 
        }
        return userFind.get(); 
    }

    public UserEntity checkStatus(String email){

        UserEntity userFind = userRepos.findByEmail(email).orElse(null);

        if(userFind == null){
            throw new RuntimeException("User Not Found for this email :: " + email); 
        }      

        return userRepos.findByEmail(email).get(); 
    }

    public void removeOneUser(Long Id){        
        userRepos.deleteById(Id);
    }
}
