package trabalho.kabummm.request.fornecedor;

import lombok.Getter;

@Getter
public class CriarFornecedorRequest {
    private String nome;
    private Long quantidadeItensFornecedor;
    private Double preco;
    private Long idMaterial;
    private Long idMedida;
}
