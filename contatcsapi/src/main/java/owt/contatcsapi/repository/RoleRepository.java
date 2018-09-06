package owt.contatcsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import owt.contatcsapi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
