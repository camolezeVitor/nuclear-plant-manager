package trabalho.kabummm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trabalho.kabummm.enums.TipoSecundarioVaretaEnum;
import trabalho.kabummm.enums.TipoVaretaEnum;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "varetas", schema = "public")
public class VaretasEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoVaretaEnum tipoDaVareta;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_secundario")
    private TipoSecundarioVaretaEnum tipoSecundario;
}
