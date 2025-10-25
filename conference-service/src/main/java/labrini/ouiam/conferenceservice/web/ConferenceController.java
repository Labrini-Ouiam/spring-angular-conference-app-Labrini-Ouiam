package labrini.ouiam.conferenceservice.web;

import labrini.ouiam.conferenceservice.Repository.ConferenceRepository;
import labrini.ouiam.conferenceservice.entities.Conference;
import labrini.ouiam.conferenceservice.feign.KeynoteRestClient;
import labrini.ouiam.conferenceservice.model.Keynote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConferenceController {

    private final ConferenceRepository conferenceRepository;
    private final KeynoteRestClient keynoteRestClient;

    public ConferenceController(ConferenceRepository conferenceRepository, KeynoteRestClient keynoteRestClient) {
        this.conferenceRepository = conferenceRepository;
        this.keynoteRestClient = keynoteRestClient;
    }

    // 🟢 1. Récupérer toutes les conférences
    @GetMapping("/conferences")
    public List<Conference> getAllConferences() {
        List<Conference> conferences = conferenceRepository.findAll();
        // Pour chaque conférence, on enrichit avec les infos du keynote
        conferences.forEach(conf -> {
            if (conf.getKeynoteId() != null) {
                Keynote keynote = keynoteRestClient.getKeynoteById(conf.getKeynoteId());
                conf.setKeynote(keynote);
            }
        });
        return conferences;
    }

    // 🟢 2. Récupérer une conférence par ID
    @GetMapping("/conferences/{id}")
    public ResponseEntity<Conference> getConferenceById(@PathVariable Long id) {
        return conferenceRepository.findById(id)
                .map(conf -> {
                    if (conf.getKeynoteId() != null) {
                        Keynote keynote = keynoteRestClient.getKeynoteById(conf.getKeynoteId());
                        conf.setKeynote(keynote);
                    }
                    return ResponseEntity.ok(conf);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 🟡 3. Créer une nouvelle conférence
    @PostMapping("/conferences")
    public ResponseEntity<Conference> createConference(@RequestBody Conference conference) {
        Conference saved = conferenceRepository.save(conference);
        return ResponseEntity.ok(saved);
    }

    // 🟠 4. Mettre à jour une conférence existante
    @PutMapping("/conferences/{id}")
    public ResponseEntity<Conference> updateConference(@PathVariable Long id, @RequestBody Conference updated) {
        return conferenceRepository.findById(id)
                .map(existing -> {
                    existing.setTitre(updated.getTitre());
                    existing.setType(updated.getType());
                    existing.setDate(updated.getDate());
                    existing.setDuree(updated.getDuree());
                    existing.setNbInscrits(updated.getNbInscrits());
                    existing.setScore(updated.getScore());
                    existing.setKeynoteId(updated.getKeynoteId());
                    Conference saved = conferenceRepository.save(existing);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔴 5. Supprimer une conférence
    @DeleteMapping("/conferences/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable Long id) {
        if (!conferenceRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        conferenceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
