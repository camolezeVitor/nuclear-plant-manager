package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalho.kabummm.dto.funcionario.FuncionarioDto;
import trabalho.kabummm.request.funcionario.FuncionarioRequest;
import trabalho.kabummm.service.FuncionarioService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @GetMapping(path = "/buscar-todos")
    public ResponseEntity<List<FuncionarioDto>> buscarTodosFuncionarios() {
        return this.funcionarioService.buscarTodosFuncionarios();
    }

    @GetMapping(path = "/buscar-por-id/{id}")
    public ResponseEntity<FuncionarioDto> buscarFuncionarioPorId(@PathVariable Long id) {
        return this.funcionarioService.buscarFuncionarioPorId(id);
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarFuncionario(@RequestBody FuncionarioRequest funcionarioRequest) {
        return this.funcionarioService.cadastrarFuncionario(funcionarioRequest);
    }

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<HttpStatus> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioRequest funcionarioRequest) {
        return this.funcionarioService.atualizarFuncionario(id, funcionarioRequest);
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        this.funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }

}
