package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.fornecedor.FornecedorDto;
import trabalho.kabummm.dto.varetas.FornecedoresVaretasDto;
import trabalho.kabummm.dto.varetas.VaretasDto;
import trabalho.kabummm.entity.FornecedorEntity;
import trabalho.kabummm.entity.VaretasEntity;
import trabalho.kabummm.entity.VaretasFornecedorEntity;
import trabalho.kabummm.entity.VaretasProfundidadeEntity;
import trabalho.kabummm.enums.TipoSecundarioVaretaEnum;
import trabalho.kabummm.enums.TipoVaretaEnum;
import trabalho.kabummm.repository.FornecedorEntityRepository;
import trabalho.kabummm.repository.VaretasEntityRepository;
import trabalho.kabummm.repository.VaretasFornecedorEntityRepository;
import trabalho.kabummm.repository.VaretasProfundidadeEntityRepository;
import trabalho.kabummm.request.varetas.ProfundidadeVaretasRequest;
import trabalho.kabummm.request.varetas.VaretasRequest;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VaretasService {
    
    private final VaretasEntityRepository varetasEntityRepository;
    private final VaretasProfundidadeEntityRepository varetasProfundidadeEntityRepository;
    private final VaretasFornecedorEntityRepository varetasFornecedorEntityRepository;
    private final FornecedorEntityRepository fornecedorEntityRepository;

    public ResponseEntity<List<VaretasDto>> buscarTodasAsVaretas() {
        List<VaretasEntity> varetas = this.varetasEntityRepository.findAll();
        varetas.sort(Comparator.comparing(VaretasEntity::getId));
        if(varetas.isEmpty())throw new RuntimeException("Nenhuma vareta encontrada");
        return ResponseEntity.ok(VaretasDto.converter(varetas));
    }

    @Transactional
    public void mudarTipoVareta(Long id, VaretasRequest varetaRequest) {
        VaretasEntity vareta = this.varetasEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vareta não encontrada"));
        if(varetaRequest.getTipoDaVareta().equals(TipoVaretaEnum.VARETA_DE_COMBUSTIVEL.name())) {
            vareta.setTipoDaVareta(TipoVaretaEnum.VARETA_DE_COMBUSTIVEL);
        }
        if(varetaRequest.getTipoDaVareta().equals(TipoVaretaEnum.VARETA_DE_CONTROLE.name())) {
            vareta.setTipoDaVareta(TipoVaretaEnum.VARETA_DE_CONTROLE);
            if(varetaRequest.getTipoSecundario().equals(TipoSecundarioVaretaEnum.VARETA_ABSOLUTA.name())){
                vareta.setTipoSecundario(TipoSecundarioVaretaEnum.VARETA_ABSOLUTA);
            } else {
                vareta.setTipoSecundario(TipoSecundarioVaretaEnum.VARETA_PARCIAL);
            }
        }
        if(varetaRequest.getTipoDaVareta().equals(TipoVaretaEnum.VARETA_DE_MEDIACAO.name())) {
            vareta.setTipoDaVareta(TipoVaretaEnum.VARETA_DE_MEDIACAO);
        }
        this.varetasEntityRepository.save(vareta);
    }

    @Transactional
    public void mudarProfundidadeVaretas(ProfundidadeVaretasRequest profundidade) {
        VaretasProfundidadeEntity varetasProfundidade = this.varetasProfundidadeEntityRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Profundidade não encontrada"));
        varetasProfundidade.setProfundidadeVaretasDeControleRapido(profundidade.getProfundidadeVaretasDeControleRapido());
        varetasProfundidade.setProfundidadeVaretasDeControleAbsoluto(profundidade.getProfundidadeVaretasDeControleAbsoluto());

        this.varetasProfundidadeEntityRepository.save(varetasProfundidade);
    }

    @Transactional
    public void mudarFornecedorDoTipoVareta(String tipoVareta, Long idFornecedor) {
        VaretasFornecedorEntity tipoDaVareta = this.varetasFornecedorEntityRepository.findByTipoDaVareta(TipoVaretaEnum.valueOf(tipoVareta))
                .orElseThrow(() -> new RuntimeException("Vareta não encontrada"));
        FornecedorEntity fornecedor = this.fornecedorEntityRepository.findById(idFornecedor)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        tipoDaVareta.setFornecedor(fornecedor);
        this.varetasFornecedorEntityRepository.save(tipoDaVareta);
    }

    public ResponseEntity<FornecedoresVaretasDto> buscarFornecedoresVaretas() {
        List<FornecedorEntity> fornecedoresVaretasControle = this.fornecedorEntityRepository
                .findAllByMaterial_TipoMaterial_Codigo("BARRA_CONTROLE");
        List<FornecedorEntity> fornecedoresVaretasCombustivel = this.fornecedorEntityRepository
                .findAllByMaterial_TipoMaterial_Codigo("BARRA_COMBUSTIVEL");
        List<FornecedorEntity> fornecedoresVaretasMediacao = this.fornecedorEntityRepository
                .findAllByMaterial_TipoMaterial_Codigo("BARRA_MEDIADORA");

        Optional<VaretasFornecedorEntity> optionalFornecedorAtualVaretaControle = this.varetasFornecedorEntityRepository
                .findByTipoDaVareta(TipoVaretaEnum.VARETA_DE_CONTROLE);
        Optional<VaretasFornecedorEntity> optionalFornecedorAtualVaretaCombustivel = this.varetasFornecedorEntityRepository
                .findByTipoDaVareta(TipoVaretaEnum.VARETA_DE_COMBUSTIVEL);
        Optional<VaretasFornecedorEntity> optionalFornecedorAtualVaretaMediacao = this.varetasFornecedorEntityRepository
                .findByTipoDaVareta(TipoVaretaEnum.VARETA_DE_MEDIACAO);

        FornecedorEntity fornecedorAtualVaretaMediacao = new FornecedorEntity();
        FornecedorEntity fornecedorAtualVaretaControle = new FornecedorEntity();
        FornecedorEntity fornecedorAtualVaretaCombustivel = new FornecedorEntity();

        if(optionalFornecedorAtualVaretaMediacao.isPresent()){
            fornecedorAtualVaretaMediacao = optionalFornecedorAtualVaretaMediacao.get().getFornecedor();
        }
        if(optionalFornecedorAtualVaretaControle.isPresent()){
            fornecedorAtualVaretaControle = optionalFornecedorAtualVaretaControle.get().getFornecedor();
        }
        if(optionalFornecedorAtualVaretaCombustivel.isPresent()){
            fornecedorAtualVaretaCombustivel = optionalFornecedorAtualVaretaCombustivel.get().getFornecedor();
        }

        fornecedoresVaretasControle.remove(fornecedorAtualVaretaControle);
        fornecedoresVaretasCombustivel.remove(fornecedorAtualVaretaCombustivel);
        fornecedoresVaretasMediacao.remove(fornecedorAtualVaretaMediacao);

        FornecedoresVaretasDto fornecedoresVaretasDto = new FornecedoresVaretasDto();

        fornecedoresVaretasDto.setFornecedorAtualVaretasDeControle(new FornecedorDto(fornecedorAtualVaretaControle));
        fornecedoresVaretasDto.setFornecedorAtualVaretasDeCombustivel(new FornecedorDto(fornecedorAtualVaretaCombustivel));
        fornecedoresVaretasDto.setFornecedorAtualVaretasMediadores(new FornecedorDto(fornecedorAtualVaretaMediacao));
        fornecedoresVaretasDto.setFornecedoresVaretasDeControle(FornecedorDto.converter(fornecedoresVaretasControle));
        fornecedoresVaretasDto.setFornecedoresVaretasDeCombustivel(FornecedorDto.converter(fornecedoresVaretasCombustivel));
        fornecedoresVaretasDto.setFornecedoresVaretasMediadores(FornecedorDto.converter(fornecedoresVaretasMediacao));

        return ResponseEntity.ok(fornecedoresVaretasDto);
    }
}
