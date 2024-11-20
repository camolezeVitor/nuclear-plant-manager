package trabalho.kabummm.dto.dependenciaMedida;

import lombok.Getter;
import trabalho.kabummm.dto.material.MaterialDto;
import trabalho.kabummm.dto.medida.MedidaDto;
import trabalho.kabummm.entity.DependenciaMedidaEntity;

import java.util.List;

@Getter
public class DependenciaMedidaDto {
    private final Long id;
    private final String codigoDoSetor;
    private final Long quantidade;
    private final MedidaDto medida;
    private final MaterialDto material;

    public DependenciaMedidaDto(DependenciaMedidaEntity dependenciaMedidaEntity){
        this.id = dependenciaMedidaEntity.getId();
        this.codigoDoSetor = dependenciaMedidaEntity.getSetor().getCodigoSetor();
        this.quantidade = dependenciaMedidaEntity.getQuantidade();
        this.medida = new MedidaDto(dependenciaMedidaEntity.getMedida());
        this.material = new MaterialDto(dependenciaMedidaEntity.getMaterial());
    }

    public static List<DependenciaMedidaDto> converter(List<DependenciaMedidaEntity> lista){
        return lista.stream().map(DependenciaMedidaDto::new).toList();
    }
}
