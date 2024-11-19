package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalho.kabummm.dto.fornecedor.FornecedorDto;
import trabalho.kabummm.request.fornecedor.CriarFornecedorRequest;
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
    public ResponseEntity<FornecedorDto> cadastrarFornecedor(@RequestBody CriarFornecedorRequest criarFornecedorRequest) {
        return null;
    }

    @PutMapping(path = "/atualizar/{id}")
    public ResponseEntity<HttpStatus> atualizarFornecedor(@PathVariable Long id, @RequestBody CriarFornecedorRequest criarFornecedorRequest) {
        //this.fornecedorService.atualizarFornecedor(id, criarFornecedorRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
