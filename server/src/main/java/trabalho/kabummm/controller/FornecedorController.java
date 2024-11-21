package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalho.kabummm.dto.fornecedor.FornecedorDto;
import trabalho.kabummm.request.fornecedor.FornecedorRequest;
import trabalho.kabummm.service.FornecedorService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping(path = "/buscar-todos")
    public ResponseEntity<List<FornecedorDto>> buscarTodosFornecedores() {
        return this.fornecedorService.buscarTodosFornecedores();
    }

    @GetMapping(path = "/buscar-por-id/{id}")
    public ResponseEntity<FornecedorDto> buscarFornecedorPorId(@PathVariable Long id) {
        return this.fornecedorService.buscarFornecedorPorId(id);
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<HttpStatus> cadastrarFornecedor(@RequestBody FornecedorRequest fornecedorRequest) {
        return this.fornecedorService.cadastrarFornecedor(fornecedorRequest);
    }

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<HttpStatus> atualizarFornecedor(@PathVariable Long id, @RequestBody FornecedorRequest fornecedorRequest) {
        return this.fornecedorService.atualizarFornecedor(id, fornecedorRequest);
    }

    @DeleteMapping(path = "/deletar/{id}")
    public ResponseEntity<HttpStatus> deletarFornecedor(@PathVariable Long id) {
        return this.fornecedorService.deletarFornecedor(id);
    }
}
