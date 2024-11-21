package trabalho.kabummm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "setor", schema = "public")
public class SetorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setor_id_seq")
    @SequenceGenerator(name = "setor_id_seq", sequenceName = "setor_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "codigo_setor")
    private String codigoSetor;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade_item_produzido")
    private Long quantidadeItensProduzidos;

    @Column(name = "maximo_funcionarios")
    private Long maximoFuncionarios;

    @ManyToOne
    @JoinColumn(name = "id_medida")
    private MedidaEntity medida;

    @ManyToOne
    @JoinColumn(name = "id_tipo_setor")
    private TipoSetorEntity tipoSetor;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private MaterialEntity material;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DependenciaMedidaEntity> dependencias;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FuncionarioEntity> funcionarios;
}
