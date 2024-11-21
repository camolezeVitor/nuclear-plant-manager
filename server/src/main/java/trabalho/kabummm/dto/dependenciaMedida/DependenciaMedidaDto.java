package trabalho.kabummm.dto.dependenciaMedida;

import lombok.Getter;
import lombok.Setter;
import trabalho.kabummm.dto.material.MaterialDto;
import trabalho.kabummm.dto.medida.MedidaDto;
import trabalho.kabummm.entity.DependenciaMedidaEntity;

import java.util.List;

@Getter
@Setter
public class DependenciaMedidaDto {
    private final Long id;
    private final String codigoDoSetor;
    private final Long qtd;
    private final MedidaDto medida;
    private final MaterialDto material;
    private Long quantidadeProferidaPorProvedores;
    private Boolean dependenciaAtendida;

    public DependenciaMedidaDto(DependenciaMedidaEntity dependenciaMedidaEntity){
        this.id = dependenciaMedidaEntity.getId();
        this.codigoDoSetor = dependenciaMedidaEntity.getSetor().getCodigoSetor();
        this.qtd = dependenciaMedidaEntity.getQuantidade();
        this.medida = new MedidaDto(dependenciaMedidaEntity.getMedida());
        this.material = new MaterialDto(dependenciaMedidaEntity.getMaterial());
        this.quantidadeProferidaPorProvedores = 0L;
        this.dependenciaAtendida = false;
    }

    public static List<DependenciaMedidaDto> converter(List<DependenciaMedidaEntity> lista){
        return lista.stream().map(DependenciaMedidaDto::new).toList();
    }
}
