package owt.contatcsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import owt.contatcsapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}