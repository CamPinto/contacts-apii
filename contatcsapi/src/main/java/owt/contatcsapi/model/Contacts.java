package owt.contatcsapi.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "contacts")
@EntityListeners(AuditingEntityListener.class)
public class Contacts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_contact;

    @NotBlank
    private String lastname;

    @NotBlank
    private String firstname;


    private String fullname;

    @NotBlank
    private String address;

    @NotBlank
    private String email;


    private String mobile;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "contacts_skills",
            joinColumns = @JoinColumn(name = "contacts_id"),
            inverseJoinColumns = @JoinColumn(name = "skills_id"))
    private Set<Skills> skills = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="id_user_contact", nullable = false)
    @JsonIgnore
    private User user;


    public Contacts(){

    }

    public Contacts(@NotBlank String lastname, @NotBlank String firstname,  @NotBlank String address, @NotBlank String email,  String mobile, User user) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.fullname = firstname + " " + lastname;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.user = user;
    }

    public Contacts(@NotBlank String lastname, @NotBlank String firstname, @NotBlank String address, @NotBlank String email,  String mobile, Set<Skills> skills ) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.fullname = firstname + " " + lastname;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.skills = skills;
    }

    public Contacts(Long id_contact, @NotBlank String lastname, @NotBlank String firstname, @NotBlank String address, @NotBlank String email, String mobile) {
        this.id_contact = id_contact;
        this.lastname = lastname;
        this.firstname = firstname;
        this.fullname = firstname + " " + lastname;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }

    public Contacts(Long id_contact, @NotBlank String lastname, @NotBlank String firstname, @NotBlank String address, @NotBlank String email, String mobile, Set<Skills> skills) {
        this.id_contact = id_contact;
        this.lastname = lastname;
        this.firstname = firstname;
        this.fullname = firstname + " " + lastname;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.skills = skills;
    }
    public Long getId_contact() {
        return id_contact;
    }

    public void setId_contact(Long id_contact) {
        this.id_contact = id_contact;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getFullname() {
        return fullname;
    }

    public void setFullname() {
        this.fullname = firstname + " " + lastname;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public Set<Skills> getSkills() {

        return skills;

    }

    public void setSkills(Set<Skills> skills) {
        this.skills = skills;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}