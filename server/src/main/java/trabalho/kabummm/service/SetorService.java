package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.setor.SetorDto;
import trabalho.kabummm.dto.setor.SetorFuncionarioDto;
import trabalho.kabummm.entity.*;
import trabalho.kabummm.repository.*;
import trabalho.kabummm.request.dependencia.DependenciaRequest;
import trabalho.kabummm.request.setor.SetorRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class SetorService {

    private final SetorEntityRepository setorRepository;
    private final MedidaEntityRepository medidaRepository;
    private final TipoSetorEntityRepository tipoSetorRepository;
    private final MaterialEntityRepository materialRepository;
    private final DependenciaMedidaEntityRepository dependenciaMedidaEntityRepository;

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
    public ResponseEntity<HttpStatus> cadastrarSetor(SetorRequest setorRequest) {
        SetorEntity setor = new SetorEntity();
        setor.setCodigoSetor(setorRequest.getCodigoSetor());
        setor.setNome(setorRequest.getNome());
        setor.setQuantidadeItensProduzidos(setorRequest.getQuantidadeItensProduzidos());
        setor.setMaximoFuncionarios(setorRequest.getMaximoFuncionarios());
        setor.setMedida(buscarMedidaPorId(setorRequest.getMedida().getId()));
        setor.setTipoSetor(buscarTipoSetorPorId(setorRequest.getTipoSetor().getId()));
        setor.setMaterial(buscarMaterialPorId(setorRequest.getMaterial().getId()));

        SetorEntity salvo = this.setorRepository.save(setor);
        setorRequest.getDependencias().forEach( dependencia -> {
            DependenciaMedidaEntity novaDependencia = this.transformarDependenciaRequestEmDependenciaMedida(dependencia, salvo);
            this.dependenciaMedidaEntityRepository.save(novaDependencia);
        });
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<HttpStatus> atualizarSetor(Long id, SetorRequest setorRequest) {
        SetorEntity setor = this.setorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setor com id " + id + " não encontrado"));

        setor.setCodigoSetor(setorRequest.getCodigoSetor());
        setor.setNome(setorRequest.getNome());
        setor.setQuantidadeItensProduzidos(setorRequest.getQuantidadeItensProduzidos());
        setor.setMaximoFuncionarios(setorRequest.getMaximoFuncionarios());
        setor.setMedida(buscarMedidaPorId(setorRequest.getMedida().getId()));
        setor.setTipoSetor(buscarTipoSetorPorId(setorRequest.getTipoSetor().getId()));
        setor.setMaterial(buscarMaterialPorId(setorRequest.getMaterial().getId()));

        List<DependenciaMedidaEntity> dependenciasAntigas = setor.getDependencias();
        dependenciasAntigas.clear();

        this.setorRepository.save(setor);

        setorRequest.getDependencias().forEach(dependenciaRequest -> {
            DependenciaMedidaEntity novaDependencia = transformarDependenciaRequestEmDependenciaMedida(dependenciaRequest, setor);
            this.dependenciaMedidaEntityRepository.save(novaDependencia);
        });

        this.setorRepository.save(setor);
        return ResponseEntity.ok().build();
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

    private MaterialEntity buscarMaterialPorId(Long idMaterial) {
        return this.materialRepository.findById(idMaterial)
                .orElseThrow(() -> new RuntimeException("Material com id " + idMaterial + " não encontrado"));
    }

    private DependenciaMedidaEntity transformarDependenciaRequestEmDependenciaMedida(DependenciaRequest dependenciaRequest, SetorEntity setor) {
        DependenciaMedidaEntity novo = new DependenciaMedidaEntity();
        novo.setSetor(setor);
        novo.setMedida(buscarMedidaPorId(dependenciaRequest.getMedida().getId()));
        novo.setMaterial(buscarMaterialPorId(dependenciaRequest.getMaterial().getId()));
        novo.setQuantidade(dependenciaRequest.getQtd());
        return novo;
    }

    public ResponseEntity<SetorDto> buscarSetorPorCodigoSetor(String codigoSetor) {
        SetorEntity setor = this.setorRepository.findByCodigoSetor(codigoSetor)
                .orElseThrow(() -> new RuntimeException("Setor com código " + codigoSetor + " não encontrado"));
        return ResponseEntity.ok(new SetorDto(setor));
    }

    public ResponseEntity<List<SetorFuncionarioDto>> buscarTodosSetoresComFuncionarios() {
        List<SetorEntity> setores = this.setorRepository.findAll();
        if (setores.isEmpty()) throw new RuntimeException("Nenhum setor encontrado");
        return ResponseEntity.ok(SetorFuncionarioDto.converter(setores));
    }
}
