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
@Table(name = "dependencia_medida", schema = "public")
public class DependenciaMedidaEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codigo_setor")
    private SetorEntity setor;

    @ManyToOne
    @JoinColumn(name = "id_medida")
    private MedidaEntity medida;

    @Column(name = "quantidade")
    private Long quantidade;
}
