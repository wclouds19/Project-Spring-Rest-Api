package bootcamp.rest.services;

import bootcamp.rest.models.entities.UserEntity;
import bootcamp.rest.models.entities.UserDetailEntity;
import bootcamp.rest.models.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailServices implements UserDetailsService {

    @Autowired
    private UserRepos repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserEntity> userInfo = repository.findByEmail(email);
        
        return userInfo.map(UserDetailEntity::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));

    }
}
