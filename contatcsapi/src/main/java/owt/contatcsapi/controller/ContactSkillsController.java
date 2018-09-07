package owt.contatcsapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import owt.contatcsapi.exception.ResourceNotFoundException;
import owt.contatcsapi.model.Contacts;
import owt.contatcsapi.model.Skills;
import owt.contatcsapi.repository.ContactsRepository;
import owt.contatcsapi.repository.SkillsRepository;
import owt.contatcsapi.repository.UserRepository;

import java.util.Iterator;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ContactSkillsController {
    @Autowired
    ContactsRepository contactRepo;
    @Autowired
    SkillsRepository skillsRepo;

    @Autowired
    UserRepository userRepository;

    // Add skill to a contact
    @PutMapping("/addSkill/{id}")
    public Contacts addSkill(@PathVariable(value = "id") Long contactId,
                                  @RequestBody Long skillId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Contacts contact = contactRepo.findByUsernameAndId_contact(name, contactId);

        Skills skill = skillsRepo.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));

        Set<Skills> skills = contactRepo.findById(contactId).get().getSkills();
        skills.add(skill);

        contact.setSkills(skills);
        contact.setUser(userRepository.findByUsername(name));
        Contacts updatedContact = contactRepo.save(contact);

        return updatedContact;
    }

    // Delete skill to a contact
    @DeleteMapping("/deleteSkillFromContact/{id}")
    public Contacts deleteSkillFromContact(@PathVariable(value = "id") Long contactId,
                             @RequestBody Long skillId) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        Contacts contact = contactRepo.findByUsernameAndId_contact(name, contactId);

        Set<Skills> skills = contactRepo.findById(contactId).get().getSkills();

        for(Iterator<Skills> it = skills.iterator(); it.hasNext();){
            Skills skillToDelete = it.next();
            Long id = skillToDelete.getId_skill();
            if(id.equals(skillId) ){
                skills.remove(skillToDelete);
            }
        }

        contact.setSkills(skills);
        contact.setUser(userRepository.findByUsername(name));
        Contacts updatedContact = contactRepo.save(contact);

        return updatedContact;
    }


}
