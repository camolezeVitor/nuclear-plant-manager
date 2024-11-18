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

    public static final String [] ENDPOINTS_ADMINISTRADOR = {
        "/testeaaaa/"
    };

    public static final String [] ENDPOINTS_GERENTE = {
            "/testeaaaa2/ "
    };

    public static final String [] ENDPOINTS_ENGENHEIRO_DE_SETOR = {
            "/testeaaaa3/"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ENDPOINTS_PUBLICOS).permitAll()
                        .requestMatchers(ENDPOINTS_ADMINISTRADOR).hasRole("ADMINISTRADOR")
                        .requestMatchers(ENDPOINTS_GERENTE).hasRole("GERENTE")
                        .requestMatchers(ENDPOINTS_ENGENHEIRO_DE_SETOR).hasRole("ENGENHEIRO_DE_SETOR")
                        .anyRequest().denyAll()
                )
                .addFilterBefore(this.userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
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
