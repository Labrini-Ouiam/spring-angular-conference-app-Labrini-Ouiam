package labrini.ouiam.keynoteservice.service;

import labrini.ouiam.keynoteservice.dto.KeynoteDto;
import java.util.List;

public interface KeynoteService {
    public List<KeynoteDto> listAll();
    public KeynoteDto getById(Long id);
    public KeynoteDto create(KeynoteDto dto) ;
    public KeynoteDto update(Long id, KeynoteDto dto) ;
    public void delete(Long id);
}
