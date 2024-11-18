package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import trabalho.kabummm.request.CriarUsuarioRequest;
import trabalho.kabummm.request.LogarUsuarioRequest;
import trabalho.kabummm.service.UserService;

@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LogarUsuarioRequest loginRequest) {
        String token = this.userService.logarUsuario(loginRequest);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping(path = "/cadastrar/usuario")
    public ResponseEntity<Void> criarUsuario(@RequestBody CriarUsuarioRequest criarUsuarioRequest) {
        this.userService.criarUsuario(criarUsuarioRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

