package trabalho.kabummm.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.material.MaterialDto;
import trabalho.kabummm.entity.MaterialEntity;
import trabalho.kabummm.repository.MaterialEntityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MaterialService {

    private final MaterialEntityRepository materialEntityRepository;

    public ResponseEntity<List<MaterialDto>> buscarTodosMateriais() {
        List<MaterialEntity> materiais = this.materialEntityRepository.findAll();
        if(materiais.isEmpty()) throw new RuntimeException("Nenhum material encontrado");
        return ResponseEntity.ok(MaterialDto.converter(materiais));
    }
}
