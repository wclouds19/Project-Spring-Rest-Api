package bootcamp.rest.helpers;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import bootcamp.rest.models.entities.UserDetailEntity;

public class AuditorAwareHelpers implements AuditorAware<String>{

    @Override
    public Optional<String> getCurrentAuditor() {

        //Get to know current user login
        UserDetailEntity currentUser = (UserDetailEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(currentUser.getUsername());
    }
    
}
