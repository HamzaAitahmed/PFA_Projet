package ma.emsi.gestionnairedestaches.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String nom;

    @OneToMany(mappedBy = "ProjectTask")
    private Collection<Task> Tasks;

    @ManyToOne
    private User ProjectOwner;

    @ManyToOne
    private Team ProjectTeam;

}
