package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalho.kabummm.dto.setor.SetorDto;
import trabalho.kabummm.dto.setor.SetorFuncionarioDto;
import trabalho.kabummm.request.setor.SetorRequest;
import trabalho.kabummm.service.SetorService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<SetorDto>> buscarTodosSetores() {
        return this.setorService.buscarTodosSetores();
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<SetorDto> buscarSetorPorId(@PathVariable Long id) {
        return this.setorService.buscarSetorPorId(id);
    }

    @GetMapping("/buscar-por-codigo-setor/{codigoSetor}")
    public ResponseEntity<SetorDto> buscarSetorPorCodigoSetor(@PathVariable String codigoSetor) {
        return this.setorService.buscarSetorPorCodigoSetor(codigoSetor);
    }

    @GetMapping("/buscar-todos-com-funcionarios")
    public ResponseEntity<List<SetorFuncionarioDto>> buscarTodosSetoresComFuncionarios() {
        return this.setorService.buscarTodosSetoresComFuncionarios();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarSetor(@RequestBody SetorRequest setorRequest) {
        return this.setorService.cadastrarSetor(setorRequest);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<HttpStatus> atualizarSetor(@PathVariable Long id, @RequestBody SetorRequest setorRequest) {
        return this.setorService.atualizarSetor(id, setorRequest);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarSetor(@PathVariable Long id) {
        this.setorService.deletarSetor(id);
        return ResponseEntity.noContent().build();
    }
}
