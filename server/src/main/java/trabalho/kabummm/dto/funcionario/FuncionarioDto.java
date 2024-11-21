package trabalho.kabummm.dto.funcionario;

import lombok.Getter;
import trabalho.kabummm.dto.setor.SetorDto;
import trabalho.kabummm.entity.FuncionarioEntity;

import java.util.List;

@Getter
public class FuncionarioDto {
    private final Long id;
    private final String nome;
    private final Double salario;
    private final String cargo;
    private final Long periodoInicial;
    private final Long periodoFinal;
    private final SetorDto setor;

    public FuncionarioDto(FuncionarioEntity funcionarioEntity) {
        this.id = funcionarioEntity.getId();
        this.nome = funcionarioEntity.getNome();
        this.salario = funcionarioEntity.getSalario();
        this.cargo = funcionarioEntity.getCargo();
        this.periodoInicial = funcionarioEntity.getPeriodoInicial();
        this.periodoFinal = funcionarioEntity.getPeriodoFinal();
        this.setor = new SetorDto(funcionarioEntity.getSetor());
    }

    public static List<FuncionarioDto> converter(List<FuncionarioEntity> lista) {
        return lista.stream().map(FuncionarioDto::new).toList();
    }
}
