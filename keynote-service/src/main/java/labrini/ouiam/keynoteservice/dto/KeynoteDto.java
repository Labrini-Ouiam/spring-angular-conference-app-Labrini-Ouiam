package labrini.ouiam.keynoteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class KeynoteDto {
    Long id;
    String nom;
    String prenom;
    String email;
    String fonction;
}
