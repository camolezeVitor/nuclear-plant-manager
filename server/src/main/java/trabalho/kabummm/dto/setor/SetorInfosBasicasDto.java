package trabalho.kabummm.dto.setor;

import lombok.Getter;
import trabalho.kabummm.dto.material.MaterialDto;
import trabalho.kabummm.dto.medida.MedidaDto;
import trabalho.kabummm.dto.tipoSetor.TipoSetorDto;
import trabalho.kabummm.entity.SetorEntity;

import java.util.List;

@Getter
public class SetorInfosBasicasDto {
    private final Long id;
    private final String codigoSetor;
    private final String nome;
    private final Long quantidadeItensProduzidos;
    private final Long maximoFuncionarios;
    private final MedidaDto medida;
    private final TipoSetorDto tipoSetor;
    private final MaterialDto material;

    public SetorInfosBasicasDto(SetorEntity setorEntity) {
        this.id = setorEntity.getId();
        this.codigoSetor = setorEntity.getCodigoSetor();
        this.nome = setorEntity.getNome();
        this.quantidadeItensProduzidos = setorEntity.getQuantidadeItensProduzidos();
        this.maximoFuncionarios = setorEntity.getMaximoFuncionarios();
        this.medida = new MedidaDto(setorEntity.getMedida());
        this.tipoSetor = new TipoSetorDto(setorEntity.getTipoSetor());
        this.material = new MaterialDto(setorEntity.getMaterial());
    }

    public static List<SetorInfosBasicasDto> converter(List<SetorEntity> lista) {
        return lista.stream().map(SetorInfosBasicasDto::new).toList();
    }
}
