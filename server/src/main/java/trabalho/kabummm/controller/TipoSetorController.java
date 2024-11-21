package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho.kabummm.dto.tipoSetor.TipoSetorDto;
import trabalho.kabummm.service.TipoSetorService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tipo-setor")
public class TipoSetorController {

    private final TipoSetorService tipoSetorService;

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<TipoSetorDto>> buscarTodosTipoSetor() {
        return this.tipoSetorService.buscarTodosTipoSetor();
    }
}
