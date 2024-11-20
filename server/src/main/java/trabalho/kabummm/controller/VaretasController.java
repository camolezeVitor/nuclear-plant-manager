package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalho.kabummm.dto.varetas.VaretasDto;
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

    @PutMapping("/mudar-tipo-vareta/{id}")
    public ResponseEntity<HttpStatus> mudarTipoVareta(@PathVariable Long id, @RequestBody VaretasRequest tipo){
        this.varetasService.mudarTipoVareta(id, tipo);
        return ResponseEntity.ok().build();
    }
}
