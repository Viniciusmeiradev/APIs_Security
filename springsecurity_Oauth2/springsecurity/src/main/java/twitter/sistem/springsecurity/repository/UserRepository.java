package twitter.sistem.springsecurity.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import twitter.sistem.springsecurity.entities.User;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    
}