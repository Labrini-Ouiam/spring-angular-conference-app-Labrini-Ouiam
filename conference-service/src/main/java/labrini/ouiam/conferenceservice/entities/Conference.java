package labrini.ouiam.conferenceservice.entities;

import jakarta.persistence.*;
import labrini.ouiam.conferenceservice.model.Keynote;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Conference {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String type; // académique ou commerciale
    private Date date;
    private int duree; // en minutes
    private int nbInscrits;
    private double score;
    private Long keynoteId; // référence vers keynote-service
    @Transient
    private Keynote keynote; // association avec Keynote
    @OneToMany(mappedBy = "conference")
    private List<Review> reviews;
}
