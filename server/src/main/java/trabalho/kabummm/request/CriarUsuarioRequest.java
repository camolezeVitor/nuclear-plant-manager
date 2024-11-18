package trabalho.kabummm.request;

import lombok.Getter;
import trabalho.kabummm.enums.Rules;

@Getter
public class CriarUsuarioRequest {

    private String nome;
    private String cadastro;
    private String senha;
    private Rules rules;
}
