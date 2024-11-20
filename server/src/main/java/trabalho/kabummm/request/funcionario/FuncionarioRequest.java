package trabalho.kabummm.request.funcionario;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FuncionarioRequest {

    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Salario não pode ser nulo")
    private Double salario;

    @NotNull(message = "Cargo não pode ser nulo")
    private String cargo;

    @NotNull(message = "Periodo Inicial não pode ser nulo")
    private Long periodoInicial;

    @NotNull(message = "Periodo Final não pode ser nulo")
    private Long periodoFinal;

    @NotNull(message = "Id_Setor não pode ser nulo")
    private String codigoSetor;
}
