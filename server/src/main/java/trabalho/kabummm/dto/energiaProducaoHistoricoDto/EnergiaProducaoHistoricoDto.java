package trabalho.kabummm.dto.energiaProducaoHistoricoDto;

import lombok.Getter;
import trabalho.kabummm.entity.EnergiaProducaoHistoricoEntity;

import java.util.List;

@Getter
public class EnergiaProducaoHistoricoDto {
    private final Long id;
    private final String dataProducao;
    private final String temperatura;
    private final String energiaGerada;

    public EnergiaProducaoHistoricoDto(EnergiaProducaoHistoricoEntity energiaProducaoHistoricoEntity) {
        this.id = energiaProducaoHistoricoEntity.getId();
        this.dataProducao = energiaProducaoHistoricoEntity.getDataProducao();
        this.temperatura = energiaProducaoHistoricoEntity.getTemperatura();
        this.energiaGerada = energiaProducaoHistoricoEntity.getEnergiaGerada();
    }

    public static List<EnergiaProducaoHistoricoDto> converter(List<EnergiaProducaoHistoricoEntity> energiaProducaoHistorico) {
        return energiaProducaoHistorico.stream().map(EnergiaProducaoHistoricoDto::new).toList();
    }
}
