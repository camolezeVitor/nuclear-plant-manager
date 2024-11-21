package trabalho.kabummm.dto.conexao;

import lombok.Getter;
import lombok.Setter;
import trabalho.kabummm.dto.fornecedor.FornecedorDto;
import trabalho.kabummm.dto.setor.SetorInfosBasicasDto;
import trabalho.kabummm.entity.ConexaoEntity;

import java.util.List;

@Getter
@Setter
public class ConexaoDto {
    private final Long id;
    private final Long idProvedor;
    private final Long prioridade;
    private final String tipoConexao;
    private SetorInfosBasicasDto provedorCasoTipoConexaoSejaSetor;
    private FornecedorDto provedorCasoTipoConexaoSejaFornecedor;

    public ConexaoDto(ConexaoEntity conexaoEntity){
        this.id = conexaoEntity.getId();
        this.idProvedor = conexaoEntity.getIdProvedor();
        this.prioridade = conexaoEntity.getPrioridade();
        this.tipoConexao = conexaoEntity.getTipoConexao().name();
    }

    public static List<ConexaoDto> converter(List<ConexaoEntity> lista){
        return lista.stream().map(ConexaoDto::new).toList();
    }
}
