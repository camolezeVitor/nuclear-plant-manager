package trabalho.kabummm.request.tipoMaterial;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class TipoMaterialRequest {

    @NotNull(message = "Id não pode ser nulo")
    @Positive(message = "Id deve ser um número positivo")
    private Long id;

    @NotNull(message = "Código não pode ser nulo")
    private String codigo;

    @NotNull(message = "Descrição não pode ser nulo")
    private String descricao;
}
