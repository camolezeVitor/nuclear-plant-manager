package trabalho.kabummm.entity;

import jakarta.persistence.*;
import lombok.*;
import trabalho.kabummm.enums.TipoVaretaEnum;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "varetas_fornecedor", schema = "public")
public class VaretasFornecedorEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    @Enumerated(EnumType.STRING)
    private TipoVaretaEnum tipoDaVareta;

    @OneToOne
    @JoinColumn(name = "id_fornecedor")
    private FornecedorEntity fornecedor;
}
