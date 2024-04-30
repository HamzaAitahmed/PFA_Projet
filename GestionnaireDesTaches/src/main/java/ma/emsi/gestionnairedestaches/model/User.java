package ma.emsi.gestionnairedestaches.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String email;

    private String username,password;

    @Column(name = "ROLE")
    private String role;



    @OneToMany(mappedBy = "ProjectOwner")
    private Collection<Project> Projects;

    @OneToMany(mappedBy = "UserTask")
    private Collection<Task> Tasks;

    @ManyToMany(mappedBy = "MemberTeam")
    private Collection<Team> Teams;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }


}
