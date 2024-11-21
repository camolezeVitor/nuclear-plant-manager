package trabalho.kabummm.request.conexao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ConexaoRequest {
    @NotNull(message = "O id do provedor é obrigatório")
    @Positive(message = "O id do provedor deve ser maior que zero")
    private Long idProvedor;

    @NotNull(message = "O id do dependente é obrigatório")
    @Positive(message = "O id do dependente deve ser maior que zero")
    private Long idDependente;

    @NotNull(message = "Prioridade da conexão é obrigatória")
    @Positive(message = "Prioridade da conexão deve ser maior que zero")
    private Long prioridade;

    @NotNull(message = "Tipo da conexão é obrigatório")
    private String tipoConexao;
}
