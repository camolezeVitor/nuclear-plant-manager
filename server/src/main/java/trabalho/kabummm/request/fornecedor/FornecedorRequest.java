package trabalho.kabummm.request.fornecedor;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FornecedorRequest {

    @NotNull(message = "O nome do fornecedor não pode ser nulo")
    private String nome;

    @NotNull(message = "Quantidade de Itens Fornecedores não pode ser nulo")
    private Long quantidadeItensFornecedor;

    @NotNull(message = "Preço não pode ser nulo")
    private Double preco;

    @NotNull(message = "Id_Material não pode ser nulo")
    private Long idMaterial;

    @NotNull(message = "Id_Medida não pode ser nulo")
    private Long idMedida;
}
