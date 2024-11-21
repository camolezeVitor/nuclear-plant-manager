package trabalho.kabummm.dto.medida;

import lombok.Getter;
import trabalho.kabummm.entity.MedidaEntity;

import java.util.List;

@Getter
public class MedidaDto {

    private final Long id;
    private final String codigoMedida;
    private final String descricao;

    public MedidaDto(MedidaEntity medidaEntity) {
        this.id = medidaEntity.getId();
        this.codigoMedida = medidaEntity.getCodigoMedida();
        this.descricao = medidaEntity.getDescricao();
    }

    public static List<MedidaDto> converter(List<MedidaEntity> medidas) {
        return medidas.stream().map(MedidaDto::new).toList();
    }
}
