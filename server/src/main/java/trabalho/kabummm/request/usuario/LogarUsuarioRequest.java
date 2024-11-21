package trabalho.kabummm.request.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LogarUsuarioRequest {

        @NotNull(message = "Cadastro para logar não pode ser nulo")
        private String cadastro;

        @NotNull(message = "Senha para logar não pode ser nulo")
        private String senha;
}
