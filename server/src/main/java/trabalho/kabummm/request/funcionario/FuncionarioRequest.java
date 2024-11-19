package trabalho.kabummm.request.funcionario;

import lombok.Getter;

@Getter
public class FuncionarioRequest {
    private String nome;
    private Double salario;
    private String cargo;
    private Long periodoInicial;
    private Long periodoFinal;
    private Long idSetor;
}
