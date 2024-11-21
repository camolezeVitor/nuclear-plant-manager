package trabalho.kabummm.request.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import trabalho.kabummm.enums.RulesEnum;

@Getter
public class CriarUsuarioRequest {

    @NotNull(message = "Nome para criar usuário não pode ser nulo")
    private String nome;

    @NotNull(message = "Cadastro para criar usuário não pode ser nulo")
    private String cadastro;

    @NotNull(message = "Senha do usuário não pode ser nulo")
    private String senha;

    private RulesEnum rulesEnum;
}
