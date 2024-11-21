package trabalho.kabummm.request.funcionario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class FuncionarioRequest {

    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Salario não pode ser nulo")
    @Positive(message = "Salario deve ser um número positivo")
    private Double salario;

    @NotNull(message = "Cargo não pode ser nulo")
    private String cargo;

    @NotNull(message = "Periodo Inicial não pode ser nulo")
    @Positive(message = "Periodo Inicial deve ser um número positivo")
    private Long periodoInicial;

    @NotNull(message = "Periodo Final não pode ser nulo")
    @Positive(message = "Periodo Final deve ser um número positivo")
    private Long periodoFinal;

    @NotNull(message = "Id_Setor não pode ser nulo")
    private String codigoSetor;
}
