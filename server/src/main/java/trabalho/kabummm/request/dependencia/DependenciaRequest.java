package trabalho.kabummm.request.dependencia;

import lombok.Getter;
import trabalho.kabummm.request.material.MaterialRequest;
import trabalho.kabummm.request.medida.MedidaRequest;

@Getter
public class DependenciaRequest {
    private MaterialRequest material;
    private MedidaRequest medida;
    private Long qtd;
}
