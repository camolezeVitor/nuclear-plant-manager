package trabalho.kabummm.dto.tipoSetor;

import lombok.Getter;
import trabalho.kabummm.entity.TipoSetorEntity;

import java.util.List;

@Getter
public class TipoSetorDto {
    private final Long id;
    private final String codigoTipoSetor;
    private final String descricao;

    public TipoSetorDto(TipoSetorEntity tipoSetorEntity) {
        this.id = tipoSetorEntity.getId();
        this.codigoTipoSetor = tipoSetorEntity.getCodigoTipoSetor();
        this.descricao = tipoSetorEntity.getDescricao();
    }

    public static List<TipoSetorDto> converter(List<TipoSetorEntity> lista) {
        return lista.stream().map(TipoSetorDto::new).toList();
    }
}
