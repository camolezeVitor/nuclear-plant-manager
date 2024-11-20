package trabalho.kabummm.request.usuario;

import lombok.Getter;
import trabalho.kabummm.enums.RulesEnum;

@Getter
public class CriarUsuarioRequest {

    private String nome;
    private String cadastro;
    private String senha;
    private RulesEnum rulesEnum;
}
