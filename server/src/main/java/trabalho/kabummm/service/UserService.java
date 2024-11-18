package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import trabalho.kabummm.config.Security.SecurityConfiguration;
import trabalho.kabummm.config.Security.user.UserDetailsImpl;
import trabalho.kabummm.config.Token.JwtTokenService;
import trabalho.kabummm.entity.RoleEntity;
import trabalho.kabummm.entity.UserEntity;
import trabalho.kabummm.enums.Rules;
import trabalho.kabummm.repository.UserEntityRepository;
import trabalho.kabummm.request.CriarUsuarioRequest;
import trabalho.kabummm.request.LogarUsuarioRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserEntityRepository userEntityRepository;
    private final JwtTokenService jwtTokenService;
    private final SecurityConfiguration securityConfiguration;

    @Transactional
    public String logarUsuario(LogarUsuarioRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getCadastro(), loginRequest.getSenha());
        Authentication authentication = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return this.jwtTokenService.gerarToken(userDetails);
    }

    @Transactional
    public void criarUsuario(CriarUsuarioRequest criarUsuarioRequest) {
        UserEntity novoUsuario = new UserEntity();
        novoUsuario.setNomeDoUsuario(criarUsuarioRequest.getNome());
        novoUsuario.setCadastro(criarUsuarioRequest.getCadastro());
        novoUsuario.setSenha(securityConfiguration.passwordEncoder().encode(criarUsuarioRequest.getSenha()));
        novoUsuario.setRoles(List.of(RoleEntity.builder()
                .role(criarUsuarioRequest.getRules()).build()));
        this.userEntityRepository.save(novoUsuario);
    }


}