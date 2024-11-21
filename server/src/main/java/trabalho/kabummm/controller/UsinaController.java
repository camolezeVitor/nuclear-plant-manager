package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho.kabummm.dto.energiaProducaoHistoricoDto.EnergiaProducaoHistoricoDto;
import trabalho.kabummm.service.UsinaService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/usina")
public class UsinaController {

    private final UsinaService usinaService;

    @GetMapping("/buscar-registros-historico-energia")
    public ResponseEntity<List<EnergiaProducaoHistoricoDto>> buscarRegistros() {
        return this.usinaService.buscarRegistros();
    }
}
