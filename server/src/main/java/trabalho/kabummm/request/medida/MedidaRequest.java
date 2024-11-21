package trabalho.kabummm.request.medida;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MedidaRequest {
    @NotNull(message = "Id não pode ser nulo")
    private Long id;

    @NotNull(message = "Código da Medida não pode ser nulo")
    private String codigoMedida;

    @NotNull(message = "Descrição não pode ser nulo")
    private String descricao;
}
