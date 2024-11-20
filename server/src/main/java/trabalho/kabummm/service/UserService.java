package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import trabalho.kabummm.config.Security.SecurityConfiguration;
import trabalho.kabummm.config.Security.user.UserDetailsImpl;
import trabalho.kabummm.config.Token.JwtTokenService;
import trabalho.kabummm.dto.user.UserDto;
import trabalho.kabummm.entity.RoleEntity;
import trabalho.kabummm.entity.UserEntity;
import trabalho.kabummm.enums.RulesEnum;
import trabalho.kabummm.repository.*;
import trabalho.kabummm.request.usuario.CriarUsuarioRequest;
import trabalho.kabummm.request.usuario.LogarUsuarioRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final JwtTokenService jwtTokenService;
    private final SecurityConfiguration securityConfiguration;
    private final RoleEntityRepository roleEntityRepository;

    @Transactional
    public ResponseEntity<String> logarUsuario(LogarUsuarioRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getCadastro(), loginRequest.getSenha());
        Authentication authentication = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok().body(this.jwtTokenService.gerarToken(userDetails));
    }

    @Transactional
    public void criarUsuario(CriarUsuarioRequest criarUsuarioRequest) {
        UserEntity novoUsuario = new UserEntity();
        novoUsuario.setNomeDoUsuario(criarUsuarioRequest.getNome());
        novoUsuario.setCadastro(criarUsuarioRequest.getCadastro());
        novoUsuario.setSenha(securityConfiguration.passwordEncoder().encode(criarUsuarioRequest.getSenha()));
        novoUsuario.setRoles(this.roleEntityRepository.findByRole(criarUsuarioRequest.getRulesEnum()));

        this.userEntityRepository.save(novoUsuario);
    }

    public ResponseEntity<List<UserDto>> buscarTodosUsuarios() {
        List<UserEntity> users = this.userEntityRepository.findAll();
        if (users.isEmpty()) throw new RuntimeException("Nenhum usuário encontrado");
        return ResponseEntity.ok(UserDto.convert(users));
    }

    public ResponseEntity<UserDto> buscarUsuarioPorCadastro(String cadastro) {
        UserEntity user = this.userEntityRepository.findByCadastro(cadastro)
                .orElseThrow(() -> new RuntimeException("Usuário com cadastro " + cadastro + " não encontrado"));
        return ResponseEntity.ok(new UserDto(user));
    }

    @Transactional
    public void deletarUsuario(String cadastro) {
        if (!this.userEntityRepository.existsByCadastro(cadastro)) {
            throw new RuntimeException("Usuário com cadastro " + cadastro + " não encontrado");
        }
        this.userEntityRepository.deleteByCadastro(cadastro);
    }

    @Transactional
    public void atualizarCargo(String cadastro, String novoCargo) {
        UserEntity user = this.userEntityRepository.findByCadastro(cadastro)
                .orElseThrow(() -> new RuntimeException("Usuário com cadastro " + cadastro + " não encontrado"));
        RoleEntity role = this.roleEntityRepository.findByRole(RulesEnum.valueOf(novoCargo));
        user.setRoles(role);
        this.userEntityRepository.save(user);
    }
}
