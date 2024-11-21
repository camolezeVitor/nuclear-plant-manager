package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalho.kabummm.request.conexao.ConexaoRequest;
import trabalho.kabummm.service.ConexaoService;

@RestController
@AllArgsConstructor
@RequestMapping("/conexao")
public class ConexaoController {

    private final ConexaoService conexaoService;

    @PostMapping("/criar-conexao")
    public ResponseEntity<HttpStatus> criarConexaoEntreSetores(@RequestBody ConexaoRequest conexaoRequest) {
        return this.conexaoService.criarConexaoEntreSetores(conexaoRequest);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<HttpStatus> deletarConexao(@PathVariable Long id) {
        return this.conexaoService.deletarConexao(id);
    }

}
