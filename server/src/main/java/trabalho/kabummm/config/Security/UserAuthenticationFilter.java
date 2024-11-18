package trabalho.kabummm.config.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import trabalho.kabummm.config.Security.user.UserDetailsImpl;
import trabalho.kabummm.config.Token.JwtTokenService;
import trabalho.kabummm.entity.UserEntity;
import trabalho.kabummm.repository.UserEntityRepository;

import java.io.IOException;
import java.util.Arrays;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(checarSeEndpointEPublico(request)){
            String token = recuperarToken(request);
            if(token != null){
                String assunto = this.jwtTokenService.buscarAssuntoDoToken(token);
                UserEntity usuario = this.userEntityRepository.findByCadastro(assunto)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
                UserDetailsImpl userDetails = new UserDetailsImpl(usuario);
                Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("Token inválido.");
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checarSeEndpointEPublico(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !Arrays.asList(SecurityConfiguration.ENDPOINTS_PUBLICOS).contains(requestURI);
    }
}
