package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.setor.SetorDto;
import trabalho.kabummm.entity.MedidaEntity;
import trabalho.kabummm.entity.SetorEntity;
import trabalho.kabummm.entity.TipoSetorEntity;
import trabalho.kabummm.repository.MedidaEntityRepository;
import trabalho.kabummm.repository.SetorEntityRepository;
import trabalho.kabummm.repository.TipoSetorEntityRepository;
import trabalho.kabummm.request.setor.SetorRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class SetorService {

    private final SetorEntityRepository setorRepository;
    private final MedidaEntityRepository medidaRepository;
    private final TipoSetorEntityRepository tipoSetorRepository;

    public ResponseEntity<List<SetorDto>> buscarTodosSetores() {
        List<SetorEntity> setores = this.setorRepository.findAll();
        if (setores.isEmpty()) throw new RuntimeException("Nenhum setor encontrado");
        return ResponseEntity.ok(SetorDto.converter(setores));
    }

    public ResponseEntity<SetorDto> buscarSetorPorId(Long id) {
        SetorEntity setor = this.setorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setor com id " + id + " não encontrado"));
        return ResponseEntity.ok(new SetorDto(setor));
    }

    @Transactional
    public ResponseEntity<SetorDto> cadastrarSetor(SetorRequest setorRequest) {
        SetorEntity setor = new SetorEntity();
        setor.setCodigo_setor(setorRequest.getCodigo_setor());
        setor.setNome(setorRequest.getNome());
        setor.setQuantidadeItensProduzidos(setorRequest.getQuantidadeItensProduzidos());
        setor.setMaximoFuncionarios(setorRequest.getMaximoFuncionarios());
        setor.setMedida(buscarMedidaPorId(setorRequest.getIdMedida()));
        setor.setTipoSetor(buscarTipoSetorPorId(setorRequest.getIdTipoSetor()));

        SetorEntity salvo = this.setorRepository.save(setor);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SetorDto(salvo));
    }

    @Transactional
    public ResponseEntity<SetorDto> atualizarSetor(Long id, SetorRequest setorRequest) {
        SetorEntity setor = this.setorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setor com id " + id + " não encontrado"));

        setor.setCodigo_setor(setorRequest.getCodigo_setor());
        setor.setNome(setorRequest.getNome());
        setor.setQuantidadeItensProduzidos(setorRequest.getQuantidadeItensProduzidos());
        setor.setMaximoFuncionarios(setorRequest.getMaximoFuncionarios());
        setor.setMedida(buscarMedidaPorId(setorRequest.getIdMedida()));
        setor.setTipoSetor(buscarTipoSetorPorId(setorRequest.getIdTipoSetor()));

        SetorEntity atualizado = setorRepository.save(setor);
        return ResponseEntity.ok(new SetorDto(atualizado));
    }

    @Transactional
    public void deletarSetor(Long id) {
        if (!setorRepository.existsById(id)) {
            throw new RuntimeException("Setor com id " + id + " não encontrado");
        }
        this.setorRepository.deleteById(id);
    }

    private MedidaEntity buscarMedidaPorId(Long idMedida) {
        return this.medidaRepository.findById(idMedida)
                .orElseThrow(() -> new RuntimeException("Medida com id " + idMedida + " não encontrada"));
    }

    private TipoSetorEntity buscarTipoSetorPorId(Long idTipoSetor) {
        return this.tipoSetorRepository.findById(idTipoSetor)
                .orElseThrow(() -> new RuntimeException("Tipo de Setor com id " + idTipoSetor + " não encontrado"));
    }
}
