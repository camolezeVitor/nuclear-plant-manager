package trabalho.kabummm.dto.reator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UsinaMensagensDto {

    private Double energiaGerada;
    private List<String> mensagens;
}
