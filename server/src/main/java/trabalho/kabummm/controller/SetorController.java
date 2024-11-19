package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalho.kabummm.dto.setor.SetorDto;
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
        return setorService.buscarTodosSetores();
    }

    @GetMapping("/buscar-por-id/{id}")
    public ResponseEntity<SetorDto> buscarSetorPorId(@PathVariable Long id) {
        return setorService.buscarSetorPorId(id);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<SetorDto> cadastrarSetor(@RequestBody SetorRequest setorRequest) {
        return setorService.cadastrarSetor(setorRequest);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<SetorDto> atualizarSetor(@PathVariable Long id, @RequestBody SetorRequest setorRequest) {
        return setorService.atualizarSetor(id, setorRequest);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarSetor(@PathVariable Long id) {
        setorService.deletarSetor(id);
        return ResponseEntity.noContent().build();
    }
}
