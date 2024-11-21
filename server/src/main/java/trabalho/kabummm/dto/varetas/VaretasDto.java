package trabalho.kabummm.dto.varetas;

import lombok.Getter;
import trabalho.kabummm.entity.VaretasEntity;

import java.util.List;

@Getter
public class VaretasDto {
    private final Long id;
    private final String tipoDaVareta;
    private final String tipoSecundario;

    public VaretasDto(VaretasEntity varetasEntity) {
        this.id = varetasEntity.getId();
        this.tipoDaVareta = varetasEntity.getTipoDaVareta().toString();
        this.tipoSecundario = varetasEntity.getTipoSecundario() == null ? null : varetasEntity.getTipoSecundario().toString();
    }

    public static List<VaretasDto> converter(List<VaretasEntity> varetasEntityList) {
        return varetasEntityList.stream().map(VaretasDto::new).toList();
    }
}
