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
@Table(name = "tipo_setor", schema = "public")
public class TipoSetorEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "codigo_tipo_setor")
    private String codigoTipoSetor;

    @Column(name = "descricao_setor")
    private String descricao;
}
