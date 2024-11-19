package trabalho.kabummm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;

    @Column(name = "quantidade")
    private Long quantidade;

    @Column(name = "horario_producao")
    private String horarioProducao;
}
