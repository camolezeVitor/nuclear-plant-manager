package trabalho.kabummm.request.dependencia;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import trabalho.kabummm.request.material.MaterialRequest;
import trabalho.kabummm.request.medida.MedidaRequest;

@Getter
public class DependenciaRequest {

    private MaterialRequest material;

    private MedidaRequest medida;

    @Positive(message = "Quantidade deve ser um número positivo")
    private Long qtd;
}
