package trabalho.kabummm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trabalho.kabummm.dto.user.UserDto;
import trabalho.kabummm.request.usuario.CriarUsuarioRequest;
import trabalho.kabummm.request.usuario.LogarUsuarioRequest;
import trabalho.kabummm.service.UserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LogarUsuarioRequest loginRequest) {
        return this.userService.logarUsuario(loginRequest);
    }

    @GetMapping(path = "/buscar-todos")
    public ResponseEntity<List<UserDto>> buscarTodosUsuarios() {
        return this.userService.buscarTodosUsuarios();
    }

    @GetMapping(path = "/buscar-usuario-por-cadastro/{cadastro}")
    public ResponseEntity<UserDto> buscarUsuarioPorCadastro(@PathVariable String cadastro) {
        return this.userService.buscarUsuarioPorCadastro(cadastro);
    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Void> criarUsuario(@RequestBody CriarUsuarioRequest criarUsuarioRequest) {
        this.userService.criarUsuario(criarUsuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(path = "/deletar/{cadastro}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String cadastro) {
        this.userService.deletarUsuario(cadastro);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/atualizar-cargo/{cadastro}/{novoCargo}")
    public ResponseEntity<Void> atualizarCargo(@PathVariable String cadastro, @PathVariable String novoCargo) {
        this.userService.atualizarCargo(cadastro, novoCargo);
        return ResponseEntity.noContent().build();
    }

}

