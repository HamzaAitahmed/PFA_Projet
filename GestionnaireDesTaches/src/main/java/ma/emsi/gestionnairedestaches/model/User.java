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

    private String nom,password;


    @OneToMany(mappedBy = "ProjectOwner")
    private Collection<Project> Projects;

    @OneToMany(mappedBy = "UserTask")
    private Collection<Task> Tasks;

    @ManyToMany(mappedBy = "MemberTeam")
    private Collection<Team> Teams;


}
