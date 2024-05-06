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

    @Column(name = "photo", nullable = true)
    private String profilePicture;

    @OneToMany(mappedBy = "ProjectOwner")
    private Collection<Project> Projects;

    @OneToMany(mappedBy = "UserTask")
    private Collection<Task> Tasks;

    @OneToMany(mappedBy = "Leader")
    private Collection<Team> MyTeams;

    @ManyToMany(mappedBy = "Members")
    private Collection<Team> Teams;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

}
