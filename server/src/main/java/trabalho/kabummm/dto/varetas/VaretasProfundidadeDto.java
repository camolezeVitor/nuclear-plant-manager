package trabalho.kabummm.dto.varetas;

import lombok.Getter;
import trabalho.kabummm.entity.VaretasProfundidadeEntity;

@Getter
public class VaretasProfundidadeDto {
    private final Long profundidadeVaretasDeControleRapido;
    private final Long profundidadeVaretasDeControleAbsoluto;

    public VaretasProfundidadeDto(VaretasProfundidadeEntity varetasProfundidadeEntity) {
        this.profundidadeVaretasDeControleRapido = varetasProfundidadeEntity.getProfundidadeVaretasDeControleRapido();
        this.profundidadeVaretasDeControleAbsoluto = varetasProfundidadeEntity.getProfundidadeVaretasDeControleAbsoluto();
    }
}
