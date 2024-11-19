package trabalho.kabummm.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.medida.MedidaDto;
import trabalho.kabummm.entity.MedidaEntity;
import trabalho.kabummm.repository.MedidaEntityRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MedidaService {

    private final MedidaEntityRepository medidaEntityRepository;

    public ResponseEntity<List<MedidaDto>> buscarTodasMedidas() {
        List<MedidaEntity> medidas = this.medidaEntityRepository.findAll();
        if(medidas.isEmpty()) throw new RuntimeException("Nenhuma medida encontrada");
        return ResponseEntity.ok(MedidaDto.converter(medidas));
    }
}
