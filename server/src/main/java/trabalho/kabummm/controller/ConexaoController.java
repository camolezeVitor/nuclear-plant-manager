package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho.kabummm.service.ConexaoService;

@RestController
@AllArgsConstructor
@RequestMapping("/conexao")
public class ConexaoController {

    private final ConexaoService conexaoService;


}
