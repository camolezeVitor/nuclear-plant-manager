package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.funcionario.FuncionarioDto;
import trabalho.kabummm.entity.FuncionarioEntity;
import trabalho.kabummm.repository.FuncionarioEntityRepository;
import trabalho.kabummm.repository.SetorEntityRepository;
import trabalho.kabummm.request.funcionario.FuncionarioRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class FuncionarioService {

    private final FuncionarioEntityRepository funcionarioEntityRepository;
    private final SetorEntityRepository setorEntityRepository;

    public ResponseEntity<List<FuncionarioDto>> buscarTodosFuncionarios() {
        List<FuncionarioEntity> funcionarios = funcionarioEntityRepository.findAll();
        if(funcionarios.isEmpty())throw new RuntimeException("Nenhum funcionário encontrado");
        return ResponseEntity.ok(FuncionarioDto.converter(funcionarios));
    }

    public ResponseEntity<FuncionarioDto> buscarFuncionarioPorId(Long id) {
        FuncionarioEntity funcionario = this.funcionarioEntityRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Funcionário com id "+id +" não encontrado"));
        return ResponseEntity.ok(new FuncionarioDto(funcionario));
    }

    @Transactional
    public ResponseEntity<FuncionarioDto> cadastrarFuncionario(FuncionarioRequest funcionarioRequest) {
        FuncionarioEntity funcionario = new FuncionarioEntity();
        funcionario.setNome(funcionarioRequest.getNome());
        funcionario.setSalario(funcionarioRequest.getSalario());
        funcionario.setCargo(funcionarioRequest.getCargo());
        funcionario.setPeriodoInicial(funcionarioRequest.getPeriodoInicial());
        funcionario.setPeriodoFinal(funcionarioRequest.getPeriodoFinal());
        funcionario.setSetor(this.setorEntityRepository
                .findById(funcionarioRequest.getIdSetor())
                .orElseThrow(()-> new RuntimeException("Setor não encontrado")));

        FuncionarioEntity salvo = this.funcionarioEntityRepository.save(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new FuncionarioDto(salvo));
    }

    @Transactional
    public ResponseEntity<FuncionarioDto> atualizarFuncionario(Long id, FuncionarioRequest funcionarioRequest) {
        FuncionarioEntity funcionario = this.funcionarioEntityRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário com id "+id+" não encontrado"));
        funcionario.setNome(funcionarioRequest.getNome());
        funcionario.setSalario(funcionarioRequest.getSalario());
        funcionario.setCargo(funcionarioRequest.getCargo());
        funcionario.setPeriodoInicial(funcionarioRequest.getPeriodoInicial());
        funcionario.setPeriodoFinal(funcionarioRequest.getPeriodoFinal());
        funcionario.setSetor(this.setorEntityRepository
                .findById(funcionarioRequest.getIdSetor())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado")));
        FuncionarioEntity atualizado = funcionarioEntityRepository.save(funcionario);
        return ResponseEntity.ok(new FuncionarioDto(atualizado));
    }

    @Transactional
    public void deletarFuncionario(Long id) {
        if (this.funcionarioEntityRepository.existsById(id)) {
            this.funcionarioEntityRepository.deleteById(id);
        }
    }


}
