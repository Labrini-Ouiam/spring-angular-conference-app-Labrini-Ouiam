package labrini.ouiam.keynoteservice.mapper;


import labrini.ouiam.keynoteservice.dto.KeynoteDto;
import labrini.ouiam.keynoteservice.entities.Keynote;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class KeynoteMapper {

    // Conversion Entity → DTO
    public KeynoteDto toDto(Keynote entity) {
        if (entity == null) return null;
        KeynoteDto dto = new KeynoteDto(
                entity.getId(),
                entity.getNom(),
                entity.getPrenom(),
                entity.getEmail(),
                entity.getFonction()
        );
        return dto;
    }

    // Conversion DTO → Entity
    public Keynote toEntity(KeynoteDto dto) {
        if (dto == null) return null;
        Keynote entity = new Keynote();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    // Mise à jour d'une entité existante avec les propriétés d'un DTO
    public void updateEntityFromDto(KeynoteDto dto, Keynote entity) {
        if (dto == null || entity == null) return;
        // Copie les champs ayant le même nom (nulls inclus)
        BeanUtils.copyProperties(dto, entity, "id"); // on ignore "id"
    }
}
