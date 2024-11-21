package trabalho.kabummm.request.setor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import trabalho.kabummm.request.dependencia.DependenciaRequest;
import trabalho.kabummm.request.material.MaterialRequest;
import trabalho.kabummm.request.medida.MedidaRequest;
import trabalho.kabummm.request.tipoSetor.TipoSetorRequest;

import java.util.List;

@Getter
public class SetorRequest {

    @NotNull(message = "Código do Setor não pode ser nulo")
    private String codigoSetor;

    @NotNull(message = "Nome do Setor não pode ser nulo")
    private String nome;

    @NotNull(message = "Quantidade de Itens Produzidos não pode ser nulo")
    @Positive(message = "Quantidade de Itens Produzidos deve ser um número positivo")
    private Long quantidadeItensProduzidos;

    @NotNull(message = "Máximo de Funcionários não pode ser nulo")
    @Positive(message = "Máximo de Funcionários deve ser um número positivo")
    private Long maximoFuncionarios;

    @NotNull(message = "Id_Medida não pode ser nulo")
    private MedidaRequest medida;

    @NotNull(message = "Id_TipoSetor não pode ser nulo")
    private TipoSetorRequest tipoSetor;

    @NotNull(message = "Id_Material não pode ser nulo")
    private MaterialRequest material;

    private List<DependenciaRequest> dependencias;
}
