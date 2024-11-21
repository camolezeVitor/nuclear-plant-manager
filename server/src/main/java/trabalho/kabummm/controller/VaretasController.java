package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalho.kabummm.dto.varetas.FornecedoresVaretasDto;
import trabalho.kabummm.dto.varetas.VaretasDto;
import trabalho.kabummm.dto.varetas.VaretasProfundidadeDto;
import trabalho.kabummm.request.varetas.ProfundidadeVaretasRequest;
import trabalho.kabummm.request.varetas.VaretasRequest;
import trabalho.kabummm.service.VaretasService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/varetas")
public class VaretasController {

    private final VaretasService varetasService;

    @GetMapping("/buscar-todas")
    public ResponseEntity<List<VaretasDto>> buscarTodasAsVaretas(){
        return this.varetasService.buscarTodasAsVaretas();
    }

    @GetMapping("/buscar-fornecedores-varetas")
    public ResponseEntity<FornecedoresVaretasDto> buscarFornecedoresVaretas(){
        return this.varetasService.buscarFornecedoresVaretas();
    }

    @GetMapping("/buscar-profundidade-vareta")
    public ResponseEntity<VaretasProfundidadeDto> buscarProfundidadeVareta(){
        return this.varetasService.buscarProfundidadeVareta();
    }

    @PutMapping("/mudar-profundidade")
    public ResponseEntity<HttpStatus> mudarProfundidadeVaretas(@RequestBody ProfundidadeVaretasRequest profundidade){
        this.varetasService.mudarProfundidadeVaretas(profundidade);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/mudar-fornecedor-do-tipo-vareta/{idFornecedor}")
    public ResponseEntity<HttpStatus> mudarFornecedorDoTipoVareta(@PathVariable Long idFornecedor, @RequestBody String tipoVareta){
        this.varetasService.mudarFornecedorDoTipoVareta(tipoVareta, idFornecedor);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/mudar-tipo-vareta/{id}")
    public ResponseEntity<HttpStatus> mudarTipoVareta(@PathVariable Long id, @RequestBody VaretasRequest tipo){
        this.varetasService.mudarTipoVareta(id, tipo);
        return ResponseEntity.ok().build();
    }
}
