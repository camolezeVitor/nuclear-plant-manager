package trabalho.kabummm.dto.fornecedor;

import lombok.Getter;
import trabalho.kabummm.dto.material.MaterialDto;
import trabalho.kabummm.dto.medida.MedidaDto;
import trabalho.kabummm.entity.FornecedorEntity;

import java.util.List;

@Getter
public class FornecedorDto {
    private final Long id;
    private final String nome;
    private final Long quantidadeItensFornecedor;
    private final Double preco;
    private final MaterialDto material;
    private final MedidaDto medida;

    public FornecedorDto(FornecedorEntity fornecedorEntity) {
        this.id = fornecedorEntity.getId();
        this.nome = fornecedorEntity.getNome();
        this.quantidadeItensFornecedor = fornecedorEntity.getQuantidadeItensFornecedor();
        this.preco = fornecedorEntity.getPreco();
        this.material = new MaterialDto(fornecedorEntity.getMaterial());
        this.medida = new MedidaDto(fornecedorEntity.getMedida());
    }

    public static List<FornecedorDto> converter(List<FornecedorEntity> fornecedores) {
        return fornecedores.stream().map(FornecedorDto::new).toList();
    }

}
