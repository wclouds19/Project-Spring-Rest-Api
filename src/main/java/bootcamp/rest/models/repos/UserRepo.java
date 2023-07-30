package bootcamp.rest.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import bootcamp.rest.models.entities.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);
}
