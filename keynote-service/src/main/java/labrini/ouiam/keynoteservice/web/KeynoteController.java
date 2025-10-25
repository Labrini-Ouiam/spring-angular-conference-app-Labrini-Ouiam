package labrini.ouiam.keynoteservice.web;
import labrini.ouiam.keynoteservice.dto.KeynoteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import labrini.ouiam.keynoteservice.service.KeynoteServiceImpl;

import java.util.List;

@RestController
public class KeynoteController {

    private final KeynoteServiceImpl service;

    public KeynoteController(KeynoteServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/keynotes")
    public List<KeynoteDto> all() {
        return service.listAll();
    }

    @GetMapping("/keynotes/{id}")
    public KeynoteDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/keynotes")
    public KeynoteDto create( @RequestBody KeynoteDto dto) {
        KeynoteDto created = service.create(dto);
        return created;
    }

    @PutMapping("/keynotes/{id}")
    public KeynoteDto update(@PathVariable Long id,@RequestBody KeynoteDto dto) {
        KeynoteDto updated = service.update(id, dto);
        return updated;
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

