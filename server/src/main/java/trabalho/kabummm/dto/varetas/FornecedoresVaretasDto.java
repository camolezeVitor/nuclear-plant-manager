package trabalho.kabummm.dto.varetas;

import lombok.Getter;
import lombok.Setter;
import trabalho.kabummm.dto.fornecedor.FornecedorDto;

import java.util.List;

@Getter
@Setter
public class FornecedoresVaretasDto {
    private FornecedorDto fornecedorAtualVaretasDeCombustivel;
    private List<FornecedorDto> fornecedoresVaretasDeCombustivel;
    private FornecedorDto fornecedorAtualVaretasDeControle;
    private List<FornecedorDto> fornecedoresVaretasDeControle;
    private FornecedorDto fornecedorAtualVaretasMediadores;
    private List<FornecedorDto> fornecedoresVaretasMediadores;
}
