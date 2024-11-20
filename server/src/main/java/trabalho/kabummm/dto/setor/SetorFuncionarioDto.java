package trabalho.kabummm.dto.setor;

import lombok.Getter;
import trabalho.kabummm.dto.funcionario.FuncionarioDto;
import trabalho.kabummm.entity.SetorEntity;

import java.util.List;

@Getter
public class SetorFuncionarioDto {
    private final SetorDto setor;
    private final List<FuncionarioDto> funcionarios;

    public SetorFuncionarioDto(SetorEntity setorEntity) {
        this.setor = new SetorDto(setorEntity);
        this.funcionarios = FuncionarioDto.converter(setorEntity.getFuncionarios());
    }

    public static List<SetorFuncionarioDto> converter(List<SetorEntity> lista) {
        return lista.stream().map(SetorFuncionarioDto::new).toList();
    }
}
