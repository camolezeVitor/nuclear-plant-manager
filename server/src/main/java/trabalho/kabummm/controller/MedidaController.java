package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho.kabummm.dto.medida.MedidaDto;
import trabalho.kabummm.service.MedidaService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/medidas")
public class MedidaController {

    private final MedidaService medidaService;

    @GetMapping("/buscar-todas")
    public ResponseEntity<List<MedidaDto>> buscarTodasMedidas() {
        return this.medidaService.buscarTodasMedidas();
    }
}
