package trabalho.kabummm.dto.setor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trabalho.kabummm.dto.conexao.ConexaoDto;
import trabalho.kabummm.dto.fornecedor.FornecedorDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetorConexoesDto {
    private SetorDto informacoesSetor;
    private Double realProducaoDoSetor;
    private List<SetorInfosBasicasDto> setoresDisponiveisParaFornecer;
    private List<FornecedorDto> fornecedoresDisponiveisParaFornecer;
    private List<ConexaoDto> setoresQueJaSeLigam;
}
