package trabalho.kabummm.dto.tipoMaterial;

import lombok.Getter;
import trabalho.kabummm.entity.TipoMaterialEntity;

import java.util.List;

@Getter
public class TipoMaterialDto {

    private final Long id;
    private final String codigo;
    private final String descricao;

    public TipoMaterialDto(TipoMaterialEntity tipoMaterialEntity) {
        this.id = tipoMaterialEntity.getId();
        this.codigo = tipoMaterialEntity.getCodigo();
        this.descricao = tipoMaterialEntity.getDescricao();
    }

    public static List<TipoMaterialDto> converter(List<TipoMaterialEntity> tipoMaterialEntityList) {
        return tipoMaterialEntityList.stream().map(TipoMaterialDto::new).toList();
    }
}
