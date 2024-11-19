package trabalho.kabummm.dto.setor;

import lombok.Getter;
import trabalho.kabummm.dto.medida.MedidaDto;
import trabalho.kabummm.dto.tipoSetor.TipoSetorDto;
import trabalho.kabummm.entity.SetorEntity;
import trabalho.kabummm.entity.TipoSetorEntity;

import java.util.List;

@Getter
public class SetorDto {
    private final Long id;
    private final String codigo_setor;
    private final String nome;
    private final Long quantidadeItensProduzidos;
    private final Long maximoFuncionarios;
    private final MedidaDto medida;
    private final TipoSetorDto tipoSetor;

    public SetorDto(SetorEntity setorEntity) {
        this.id = setorEntity.getId();
        this.codigo_setor = setorEntity.getCodigo_setor();
        this.nome = setorEntity.getNome();
        this.quantidadeItensProduzidos = setorEntity.getQuantidadeItensProduzidos();
        this.maximoFuncionarios = setorEntity.getMaximoFuncionarios();
        this.medida = new MedidaDto(setorEntity.getMedida());
        this.tipoSetor = new TipoSetorDto(setorEntity.getTipoSetor());
    }

    public static List<SetorDto> converter(List<SetorEntity> lista) {
        return lista.stream().map(SetorDto::new).toList();
    }
}
