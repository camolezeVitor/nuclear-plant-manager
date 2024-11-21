package trabalho.kabummm.request.varetas;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class ProfundidadeVaretasRequest {

    @NotNull(message = "Profundidade Varetas de Controle Rápido não pode ser nulo")
    @Positive(message = "Profundidade Varetas de Controle Rápido deve ser um número positivo")
    private Long profundidadeVaretasDeControleRapido;

    @NotNull(message = "Profundidade Varetas de Controle Absoluto não pode ser nulo")
    @Positive(message = "Profundidade Varetas de Controle Absoluto deve ser um número positivo")
    private Long profundidadeVaretasDeControleAbsoluto;
}
