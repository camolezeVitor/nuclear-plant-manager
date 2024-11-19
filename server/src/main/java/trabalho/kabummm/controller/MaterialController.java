package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho.kabummm.dto.material.MaterialDto;
import trabalho.kabummm.service.MaterialService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/materiais")
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/buscar-todos")
    public ResponseEntity<List<MaterialDto>> buscarTodosMateriais() {
        return this.materialService.buscarTodosMateriais();
    }
}
