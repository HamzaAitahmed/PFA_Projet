package ma.emsi.gestionnairedestaches.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String nom;

    private Integer OwnerId;

    @OneToMany(mappedBy = "ProjectTeam")
    private Collection<Project> Projects;

    @ManyToMany(mappedBy = "Teams")
    private  Collection<User> MemberTeam;
}
