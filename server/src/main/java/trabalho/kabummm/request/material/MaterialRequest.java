package trabalho.kabummm.request.material;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import trabalho.kabummm.request.tipoMaterial.TipoMaterialRequest;

@Getter
public class MaterialRequest {

    @NotNull(message = "Id não pode ser nulo")
    @Positive(message = "Id deve ser um número positivo")
    private Long id;

    @NotNull(message = "Multiplicador não pode ser nulo")
    @Positive(message = "Multiplicador deve ser um número positivo")
    private Long multiplicador;

    @NotNull(message = "Nome não pode ser nulo")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotNull(message = "Tipo Material não pode ser nulo")
    private TipoMaterialRequest tipoMaterial;
}
