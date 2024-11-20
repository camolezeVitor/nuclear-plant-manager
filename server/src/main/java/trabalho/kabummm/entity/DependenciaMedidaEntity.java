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
    @GeneratedValue(generator = "dependencia_medida_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "dependencia_medida_id_seq", sequenceName = "public.dependencia_medida_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codigo_setor", referencedColumnName = "codigo_setor")
    private SetorEntity setor;

    @ManyToOne
    @JoinColumn(name = "id_medida")
    private MedidaEntity medida;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private MaterialEntity material;

    @Column(name = "quantidade")
    private Long quantidade;
}
