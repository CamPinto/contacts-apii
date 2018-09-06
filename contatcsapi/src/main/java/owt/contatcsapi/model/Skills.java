package owt.contatcsapi.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skills")
@EntityListeners(AuditingEntityListener.class)
public class Skills implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_skill;

    @NotBlank
    private String name;

    @NotBlank
    private String level;




    @ManyToMany(fetch=FetchType.EAGER, mappedBy = "skills")
    @JsonIgnore
    private Set<Contacts> contacts = new HashSet<>();


    public Skills() {
    }

    public Skills(@NotBlank String name, @NotBlank String level) {
        this.name = name;
        this.level = level;
    }

    public Skills(@NotBlank String name, @NotBlank String level, Set<Contacts> contacts) {
        this.name = name;
        this.level = level;
        this.contacts = contacts;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }



    public Set<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contacts> contacts) {
        this.contacts = contacts;
    }
}
