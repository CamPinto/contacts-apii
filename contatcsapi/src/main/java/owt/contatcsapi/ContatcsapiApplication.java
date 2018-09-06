package owt.contatcsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import owt.contatcsapi.model.Contacts;
import owt.contatcsapi.model.Skills;
import owt.contatcsapi.repository.ContactsRepository;
import owt.contatcsapi.repository.SkillsRepository;

@SpringBootApplication
public class ContatcsapiApplication /*implements CommandLineRunner*/ {

    /*@Autowired
    private ContactsRepository contactRepository;

    @Autowired
    private SkillsRepository skillsRepository;*/

    public static void main(String[] args) {
        SpringApplication.run(ContatcsapiApplication.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception {
        Contacts contact = new Contacts("Camille", "Pinto", "Avenue des communes reuinies 78", "pintocamarobaseyahoo.fr", "05466");
        Skills sk1 = new Skills("JAVA", "PRO");
        Skills sk2 = new Skills("UML", "LOW");

        contact.getSkillsCollection().add(sk1);
        contact.getSkillsCollection().add(sk2);

        sk1.getContactsCollection().add(contact);
        sk2.getContactsCollection().add(contact);

        contactRepository.save(contact);

    }*/
}
