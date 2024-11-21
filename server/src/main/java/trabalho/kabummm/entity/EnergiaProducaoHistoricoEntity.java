package trabalho.kabummm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "energia_producao_historico", schema = "public")
public class EnergiaProducaoHistoricoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "energia_producao_historico_id_seq")
    @SequenceGenerator(name = "energia_producao_historico_id_seq", sequenceName = "energia_producao_historico_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "data_producao")
    private String dataProducao;

    @Column(name = "temperatura")
    private String temperatura;

    @Column(name = "quantidade")
    private String energiaGerada;
}
