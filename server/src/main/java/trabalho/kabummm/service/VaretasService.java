package trabalho.kabummm.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.varetas.VaretasDto;
import trabalho.kabummm.entity.VaretasEntity;
import trabalho.kabummm.enums.TipoSecundarioVaretaEnum;
import trabalho.kabummm.enums.TipoVaretaEnum;
import trabalho.kabummm.repository.VaretasEntityRepository;
import trabalho.kabummm.request.varetas.VaretasRequest;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class VaretasService {
    
    private final VaretasEntityRepository varetasEntityRepository;

    public ResponseEntity<List<VaretasDto>> buscarTodasAsVaretas() {
        List<VaretasEntity> varetas = this.varetasEntityRepository.findAll();
        varetas.sort(Comparator.comparing(VaretasEntity::getId));
        if(varetas.isEmpty())throw new RuntimeException("Nenhuma vareta encontrada");
        return ResponseEntity.ok(VaretasDto.converter(varetas));
    }

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
}
