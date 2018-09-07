package owt.contatcsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import owt.contatcsapi.exception.ResourceNotFoundException;
import owt.contatcsapi.model.Skills;
import owt.contatcsapi.repository.SkillsRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SkillsController {
    @Autowired
    SkillsRepository skillsRepository;

    // Get All Skills
    @GetMapping("/skills")
    public List<Skills> getAllSkills() {
        return skillsRepository.findAll();
    }

    // Get a Single skill
    @GetMapping("/skills/{id}")
    public Optional<Skills> getSkillsById(@PathVariable(value = "id") Long skillId) {
        return skillsRepository.findById(skillId);
    }

    // Create a new skill
    @PostMapping("/createSkill")
    public Skills createSkill(@Valid @RequestBody Skills s) {
        return skillsRepository.save(s);
    }

    //delete a skill
    @DeleteMapping("/admin/deleteSkill/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable(value = "id") Long skillId) {
       Skills sk = skillsRepository.findById(skillId)
               .orElseThrow(() -> new ResourceNotFoundException("Skill", "id", skillId));

       skillsRepository.delete(sk);

       return ResponseEntity.ok().build();
    }

}
