package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trabalho.kabummm.request.usuario.CriarUsuarioRequest;
import trabalho.kabummm.request.usuario.LogarUsuarioRequest;
import trabalho.kabummm.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LogarUsuarioRequest loginRequest) {
        return this.userService.logarUsuario(loginRequest);
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> criarUsuario(@RequestBody CriarUsuarioRequest criarUsuarioRequest) {
        this.userService.criarUsuario(criarUsuarioRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

