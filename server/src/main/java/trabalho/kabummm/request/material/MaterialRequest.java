package trabalho.kabummm.request.material;

import lombok.Getter;
import trabalho.kabummm.request.tipoMaterial.TipoMaterialRequest;

@Getter
public class MaterialRequest {
    private Long id;
    private Long multiplicador;
    private String nome;
    private TipoMaterialRequest tipoMaterial;
}
