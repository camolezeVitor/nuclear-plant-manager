package trabalho.kabummm.dto.material;

import lombok.Getter;
import trabalho.kabummm.dto.tipoMaterial.TipoMaterialDto;
import trabalho.kabummm.entity.MaterialEntity;

import java.util.List;

@Getter
public class MaterialDto {
    private final Long id;
    private final String nome;
    private final Long multiplicador;
    private final TipoMaterialDto tipoMaterial;

    public MaterialDto(MaterialEntity materialEntity) {
        this.id = materialEntity.getId();
        this.nome = materialEntity.getNome();
        this.multiplicador = materialEntity.getMultiplicador();
        this.tipoMaterial = new TipoMaterialDto(materialEntity.getTipoMaterial());
    }

    public static List<MaterialDto> converter(List<MaterialEntity> materialEntityList) {
        return materialEntityList.stream().map(MaterialDto::new).toList();
    }
}
