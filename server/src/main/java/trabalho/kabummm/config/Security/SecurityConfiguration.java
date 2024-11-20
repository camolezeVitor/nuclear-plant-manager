package trabalho.kabummm.config.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserAuthenticationFilter userAuthenticationFilter;

    @Autowired
    public SecurityConfiguration(UserAuthenticationFilter userAuthenticationFilter) {
        this.userAuthenticationFilter = userAuthenticationFilter;
    }

    public static final String [] ENDPOINTS_PUBLICOS = {
        "/users/login",
        "/users/cadastrar"
    };

    public static final String [] ENDPOINTS_ENGENHEIRO_DE_SETOR = {
            "/materiais/buscar-todos",
            "/funcionarios/buscar-todos",
            "/funcionarios/buscar-por-id/{id}",
            "/funcionarios/cadastrar",
            "/funcionarios/atualizar/{id}",
            "/funcionarios/deletar/{id}",
            "/fornecedores/buscar-todos",
            "/fornecedores/buscar-por-id/{id}",
            "/fornecedores/cadastrar",
            "/fornecedores/atualizar/{id}",
            "/fornecedores/deletar/{id}",
            "/setores/buscar-todos",
            "/setores/buscar-por-id/{id}",
            "/setores/cadastrar",
            "/setores/atualizar/{id}",
            "/setores/deletar/{id}"
    };


    public static final String [] ENDPOINTS_ADMINISTRADOR = {
            "/materiais/buscar-todos",
            "/funcionarios/buscar-todos",
            "/funcionarios/buscar-por-id/{id}",
            "/funcionarios/cadastrar",
            "/funcionarios/atualizar/{id}",
            "/funcionarios/deletar/{id}",
            "/fornecedores/buscar-todos",
            "/fornecedores/buscar-por-id/{id}",
            "/fornecedores/cadastrar",
            "/fornecedores/atualizar/{id}",
            "/fornecedores/deletar/{id}",
            "/setores/buscar-todos",
            "/setores/buscar-por-id/{id}",
            "/setores/cadastrar",
            "/setores/atualizar/{id}",
            "/setores/deletar/{id}"
    };

    public static final String[] ENDPOINTS_GERENTE = {
            "/materiais/buscar-todos",
            "/funcionarios/buscar-todos",
            "/funcionarios/buscar-por-id/{id}",
            "/funcionarios/cadastrar",
            "/funcionarios/atualizar/{id}",
            "/funcionarios/deletar/{id}",
            "/fornecedores/buscar-todos",
            "/fornecedores/buscar-por-id/{id}",
            "/fornecedores/cadastrar",
            "/fornecedores/atualizar/{id}",
            "/fornecedores/deletar/{id}",
            "/setores/buscar-todos",
            "/setores/buscar-por-id/{id}",
            "/setores/cadastrar",
            "/setores/atualizar/{id}",
            "/setores/deletar/{id}"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
