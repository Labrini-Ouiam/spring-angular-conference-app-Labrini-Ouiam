package labrini.ouiam.keynoteservice.service;

import labrini.ouiam.keynoteservice.Repository.KeynoteRepository;
import labrini.ouiam.keynoteservice.dto.KeynoteDto;
import labrini.ouiam.keynoteservice.entities.Keynote;
import labrini.ouiam.keynoteservice.exception.ResourceNotFoundException;
import labrini.ouiam.keynoteservice.mapper.KeynoteMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KeynoteServiceImpl implements KeynoteService {
    private final KeynoteRepository repository;
    private final KeynoteMapper mapper;

    public KeynoteServiceImpl(KeynoteRepository repository, KeynoteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public List<KeynoteDto> listAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public KeynoteDto getById(Long id) {
        Keynote k = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Keynote introuvable id=" + id));
        return mapper.toDto(k);
    }

    @Override
    public KeynoteDto create(KeynoteDto dto) {

        if (dto.getEmail() == null) {
            throw new IllegalArgumentException("Email requis");
        }
        if (repository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Un keynote avec cet email existe déjà");
        }
        Keynote entity = mapper.toEntity(dto);
        // ensure id null to create
        entity.setId(null);
        Keynote saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public KeynoteDto update(Long id, KeynoteDto dto) {
        Keynote existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Keynote introuvable id=" + id));
        // utiliser mapstruct pour copier les champs
        mapper.updateEntityFromDto(dto, existing);
        Keynote updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Keynote introuvable id=" + id);
        }
        repository.deleteById(id);
    }
}
