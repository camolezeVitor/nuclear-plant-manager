package trabalho.kabummm.request.setor;

import lombok.Getter;

@Getter
public class SetorRequest {
    private Long id;
    private String codigo_setor;
    private String nome;
    private Long quantidadeItensProduzidos;
    private Long maximoFuncionarios;
    private Long idMedida;
    private Long idTipoSetor;
}
