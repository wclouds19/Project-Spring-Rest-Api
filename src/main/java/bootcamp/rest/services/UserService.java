package bootcamp.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import bootcamp.rest.models.entities.User;
import bootcamp.rest.models.repos.UserRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User with email : '%s' not found!!", email)));
    }

    public User register(User user){
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword()); //encrypt password
        user.setPassword(encodePassword); 
        return userRepo.save(user);
    }
    
}
