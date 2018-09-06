package owt.contatcsapi.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import owt.contatcsapi.model.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long>{
    @Query("from User u where u.username = ?1")
    Set<Contacts> findAllByUsername(String username);

    @Query("select new Contacts(c.id_contact, c.lastname, c.firstname, c.address, c.email, c.mobile) from User u join Contacts c on c.user = u.id_user where u.username = ?1 and c.id_contact = ?2")
    Contacts findByUsernameAndId_contact(String username, Long idContact);

}
