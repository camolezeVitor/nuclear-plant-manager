package trabalho.kabummm.dto.webSocket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trabalho.kabummm.enums.StatusReatorEnum;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusUsinaFront {
    private Double dinheiroUsina;
    private StatusReatorEnum statusEnergiaProd;
    private String energiaProd;
    private String temperaturaReator;
    private String horario;
    private String dia;
    private String console;
}
