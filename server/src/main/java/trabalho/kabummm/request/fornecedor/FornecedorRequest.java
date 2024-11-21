package trabalho.kabummm.request.fornecedor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class FornecedorRequest {

    @NotNull(message = "O nome do fornecedor não pode ser nulo")
    private String nome;

    @NotNull(message = "Quantidade de Itens Fornecedores não pode ser nulo")
    @Positive(message = "Quantidade de Itens Fornecedores deve ser um número positivo")
    private Long quantidadeItensFornecedor;

    @NotNull(message = "Preço não pode ser nulo")
    @Positive(message = "Preço deve ser um número positivo")
    private Double preco;

    @NotNull(message = "Id_Material não pode ser nulo")
    @Positive(message = "Id_Material deve ser um número positivo")
    private Long idMaterial;

    @NotNull(message = "Id_Medida não pode ser nulo")
    @Positive(message = "Id_Medida deve ser um número positivo")
    private Long idMedida;
}
