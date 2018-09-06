package owt.contatcsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import owt.contatcsapi.exception.ResourceNotFoundException;
import owt.contatcsapi.model.Contacts;
import owt.contatcsapi.model.Skills;
import owt.contatcsapi.model.User;
import owt.contatcsapi.repository.ContactsRepository;
import owt.contatcsapi.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ContactController {
    @Autowired
    ContactsRepository contactRepo;

    @Autowired
    UserRepository userRepo;



    // Get All Contacts, admin only
    @GetMapping("/contacts")
    public List<Contacts> getAllContacts() {
        return contactRepo.findAll();
    }

    // Get all Contacts by User
    @GetMapping("/contactsByUser")
    public Set<Contacts> findAllByUserUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return contactRepo.findAllByUsername(name);
    }

    // Get All skills by contacts
    @GetMapping("/skillsByContact/{id}")
    public Set<Skills> getAllSkillsByContact(@PathVariable(value = "id") Long contactId) {
        return contactRepo.findById(contactId).get().getSkills();
    }

    // Get a Single contact
    @GetMapping("/contacts/{id}")
    public Contacts getContactById(@PathVariable(value = "id") Long contactId) {
        return contactRepo.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact", "id", contactId));
    }

    // Create a new contact
    @PostMapping("/createContact")
    public Contacts createContact(@Valid @RequestBody Contacts c) {
        c.setFullname();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepo.findByUsername(name);
        c.setUser(user);
        return contactRepo.save(c);
    }

    // Update a contact
    @PutMapping("/updateContact/{id}")
    public Contacts updateContact(@PathVariable(value = "id") Long contactId,
                            @RequestBody Contacts contactDetails) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Contacts contact = contactRepo.findByUsernameAndId_contact(name, contactId);
        contact.setLastname(contactDetails.getLastname());
        contact.setFirstname(contactDetails.getFirstname());
        contact.setFullname();
        contact.setAddress(contactDetails.getAddress());
        contact.setEmail(contactDetails.getEmail());
        contact.setMobile(contactDetails.getMobile());
        contact.setUser(userRepo.findByUsername(name));

        Contacts updatedContact = contactRepo.save(contact);

        return updatedContact;
    }
    // Delete a contact
    @DeleteMapping("/deleteContact/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long contactId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Contacts contact = contactRepo.findByUsernameAndId_contact(name, contactId);

        contactRepo.delete(contact);

        return ResponseEntity.ok().build();
    }
}
