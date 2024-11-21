package trabalho.kabummm.config.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import trabalho.kabummm.config.Token.JwtTokenService;
import trabalho.kabummm.repository.UserEntityRepository;

import java.io.IOException;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

//        if ("GET".equalsIgnoreCase(request.getMethod())) {
//            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
//            response.setHeader("Access-Control-Allow-Credentials", "true");
//            response.setStatus(HttpServletResponse.SC_OK);
//
//            filterChain.doFilter(request, response);
//        }
//
//        try {
//            if (!checarSeEndpointEPublico(request)) {
//                String token = recuperarToken(request);
//                if (token != null) {
//                    String assunto = this.jwtTokenService.buscarAssuntoDoToken(token);
//                    UserEntity usuario = this.userEntityRepository.findByCadastro(assunto)
//                            .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
//                    UserDetailsImpl userDetails = new UserDetailsImpl(usuario);
//                    Authentication authentication = new UsernamePasswordAuthenticationToken(
//                            userDetails.getUsername(), null, userDetails.getAuthorities());
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                } else {
//                    throw new RuntimeException("Token inválido.");
//                }
//            }
            filterChain.doFilter(request, response);
//
//        } catch (Exception ex) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Erro de autenticação: " + ex.getMessage());
//            response.getWriter().flush();
//        }
    }

}