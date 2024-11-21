package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.conexao.ConexaoDto;
import trabalho.kabummm.dto.fornecedor.FornecedorDto;
import trabalho.kabummm.dto.setor.SetorConexoesDto;
import trabalho.kabummm.dto.setor.SetorDto;
import trabalho.kabummm.dto.setor.SetorFuncionarioDto;
import trabalho.kabummm.dto.setor.SetorInfosBasicasDto;
import trabalho.kabummm.entity.*;
import trabalho.kabummm.repository.*;
import trabalho.kabummm.request.dependencia.DependenciaRequest;
import trabalho.kabummm.request.setor.SetorRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class SetorService {

    private final SetorEntityRepository setorRepository;
    private final MedidaEntityRepository medidaRepository;
    private final TipoSetorEntityRepository tipoSetorRepository;
    private final ConexaoEntityRepository conexaoRepository;
    private final MaterialEntityRepository materialRepository;
    private final FornecedorEntityRepository fornecedorRepository;
    private final DependenciaMedidaEntityRepository dependenciaMedidaEntityRepository;
    private final UsinaService usinaService;

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

    public ResponseEntity<List<SetorConexoesDto>> buscarInformacoesParaConexao() {
        this.usinaService.calcularEnergiaGeradaPeloReator();


        List<SetorEntity> setores = this.setorRepository.findAll();
        setores.sort(Comparator.comparing(SetorEntity::getId));
        if(setores.isEmpty()) throw new RuntimeException("Nenhum setor encontrado");
        List<SetorConexoesDto> setoresConexoes = new ArrayList<>();

        setores.forEach(setorSelecionado -> {
            Long quantidadeDeFuncionarios = (long) setorSelecionado.getFuncionarios().size();

            List<SetorEntity> setoresQueAtemdemAoSetorSelecionado = new ArrayList<>();
            List<FornecedorEntity> fornecedoresQueAtemdemAoSetorSelecionado = new ArrayList<>();

            setorSelecionado.getDependencias().forEach(dependencia -> {
                List<SetorEntity> setoresQueAtendemAMedida = this.setorRepository.findAllByMaterialAndMedida(dependencia.getMaterial(), dependencia.getMedida());
                setoresQueAtendemAMedida.forEach(setor -> {
                    if (!setoresQueAtemdemAoSetorSelecionado.contains(setor) && !setorSelecionado.equals(setor)) {
                        if(!this.conexaoRepository.existsByDependenteIdAndIdProvedor(setorSelecionado.getId(), setor.getId())){
                            setoresQueAtemdemAoSetorSelecionado.add(setor);
                        }
                    }
                });

                List<FornecedorEntity> fornecedores = this.fornecedorRepository.findAllByMaterialAndMedida(dependencia.getMaterial(), dependencia.getMedida());
                fornecedores.forEach(fornecedor -> {
                    if (!fornecedoresQueAtemdemAoSetorSelecionado.contains(fornecedor)) {
                        if(!this.conexaoRepository.existsByDependenteIdAndIdProvedor(setorSelecionado.getId(), fornecedor.getId())){
                            fornecedoresQueAtemdemAoSetorSelecionado.add(fornecedor);
                        }
                    }
                });
            });

            SetorConexoesDto setorConexoes = new SetorConexoesDto();
            setorConexoes.setInformacoesSetor(new SetorDto(setorSelecionado));
            setorConexoes.getInformacoesSetor().setFuncionariosTrabalhando(quantidadeDeFuncionarios);
            setorConexoes.setRealProducaoDoSetor((double) (quantidadeDeFuncionarios*setorSelecionado.getQuantidadeItensProduzidos())/setorSelecionado.getMaximoFuncionarios());
            setorConexoes.setSetoresDisponiveisParaFornecer(SetorInfosBasicasDto.converter(setoresQueAtemdemAoSetorSelecionado));
            setorConexoes.setFornecedoresDisponiveisParaFornecer(FornecedorDto.converter(fornecedoresQueAtemdemAoSetorSelecionado));

            List<ConexaoEntity> listaDeDependentesParaOSetor =  this.conexaoRepository.findAllByDependenteId(setorSelecionado.getId());
            setorConexoes.setSetoresQueJaSeLigam(ConexaoDto.converter(listaDeDependentesParaOSetor));
            setorConexoes.getSetoresQueJaSeLigam().forEach(conexao -> {
                if(conexao.getTipoConexao().equals("FORNECEDOR")){
                    conexao.setProvedorCasoTipoConexaoSejaFornecedor(new FornecedorDto(this.fornecedorRepository.findById(conexao.getIdProvedor()).get()));
                }else{
                    conexao.setProvedorCasoTipoConexaoSejaSetor(new SetorInfosBasicasDto(this.setorRepository.findById(conexao.getIdProvedor()).get()));
                }
            });

            setorConexoes.getInformacoesSetor().getDependencias().forEach(dependencia -> {
                setorConexoes.getSetoresQueJaSeLigam().forEach(dtoConexao -> {
                    if(dtoConexao.getProvedorCasoTipoConexaoSejaFornecedor() != null){
                        if(dependencia.getMaterial().equals(dtoConexao.getProvedorCasoTipoConexaoSejaFornecedor().getMaterial()) ){
                            dependencia.setQuantidadeProferidaPorProvedores(dependencia.getQuantidadeProferidaPorProvedores()
                                    + dtoConexao.getProvedorCasoTipoConexaoSejaFornecedor().getQuantidadeItensFornecedor());
                            if(dependencia.getQuantidadeProferidaPorProvedores() >= dependencia.getQtd()){
                                dependencia.setDependenciaAtendida(true);
                            }
                        }
                    }
                    if(dtoConexao.getProvedorCasoTipoConexaoSejaSetor() != null){
                        if(dependencia.getMaterial().equals(dtoConexao.getProvedorCasoTipoConexaoSejaSetor().getMaterial()) ){
                            dependencia.setQuantidadeProferidaPorProvedores(dependencia.getQuantidadeProferidaPorProvedores()
                                    + dtoConexao.getProvedorCasoTipoConexaoSejaSetor().getQuantidadeItensProduzidos());
                            if(dependencia.getQuantidadeProferidaPorProvedores() >= dependencia.getQtd()){
                                dependencia.setDependenciaAtendida(true);
                            }
                        }
                    }
                });
            });
            setoresConexoes.add(setorConexoes);
        });

        return ResponseEntity.ok(setoresConexoes);
    }

}
