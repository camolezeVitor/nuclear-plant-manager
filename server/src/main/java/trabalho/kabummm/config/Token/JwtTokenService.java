package trabalho.kabummm.config.Token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;
import trabalho.kabummm.config.Security.user.UserDetailsImpl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
    private static final String SECRET_KEY = "$%3672sbauhfhabdywadg78";
    private static final String ISSUER = "uryel-api";

    public String gerarToken(UserDetailsImpl userDetails) {
        try{
            Algorithm algoritmo = Algorithm.HMAC256(SECRET_KEY);

            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(dataHoraDeCriacao())
                    .withExpiresAt(dataHoraDeExpiracao())
                    .withSubject(userDetails.getUsername())
                    .sign(algoritmo);

        } catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao criar token.", exception);
        }

    }

    public String buscarAssuntoDoToken(String token){
        try{
            return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception e){
            throw new RuntimeException("Token inválido.");
        }
    }

    private Instant dataHoraDeCriacao(){
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant dataHoraDeExpiracao(){
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).plusHours(6).toInstant();
    }
}
