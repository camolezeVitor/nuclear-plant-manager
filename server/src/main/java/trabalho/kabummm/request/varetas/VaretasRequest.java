package trabalho.kabummm.request.varetas;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class VaretasRequest {
    @NotNull(message = "Tipo da Vareta não pode ser nulo")
    private String tipoDaVareta;

    @NotNull(message = "Tipo Secundário não pode ser nulo")
    private String tipoSecundario;
}
