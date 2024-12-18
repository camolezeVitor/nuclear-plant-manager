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
@Table(name = "medidas", schema = "public")
public class MedidaEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo_medida")
    private String codigoMedida;

    @Column(name = "descricao")
    private String descricao;
}
