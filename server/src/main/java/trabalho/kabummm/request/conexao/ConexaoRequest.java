package trabalho.kabummm.request.conexao;

import lombok.Getter;

@Getter
public class ConexaoRequest {
    private Long idProvedor;
    private Long idDependente;
    private Long prioridade;
    private String tipoConexao;
}
