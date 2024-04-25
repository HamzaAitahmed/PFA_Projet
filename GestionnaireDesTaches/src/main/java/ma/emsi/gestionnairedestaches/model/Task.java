package ma.emsi.gestionnairedestaches.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique=true)
    private String nom;

    private String description;

    @ManyToOne
    private User UserTask;

    @ManyToOne
    private Project ProjectTask;
}
