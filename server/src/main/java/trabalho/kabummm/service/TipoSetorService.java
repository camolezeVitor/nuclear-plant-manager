package trabalho.kabummm.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.tipoSetor.TipoSetorDto;
import trabalho.kabummm.repository.TipoSetorEntityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TipoSetorService {

    private final TipoSetorEntityRepository tipoSetorRepository;

    public ResponseEntity<List<TipoSetorDto>> buscarTodosTipoSetor() {
        List<TipoSetorDto> tipoSetores = TipoSetorDto.converter(this.tipoSetorRepository.findAll());
        if (tipoSetores.isEmpty()) throw new RuntimeException("Nenhum tipo de setor encontrado");
        return ResponseEntity.ok(tipoSetores);
    }
}
