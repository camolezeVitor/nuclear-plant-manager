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
@Table(name = "funcionario", schema = "public")
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "funcionario_id_seq")
    @SequenceGenerator(name = "funcionario_id_seq", sequenceName = "funcionario_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "salario")
    private Double salario;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "periodo_inicial")
    private Long periodoInicial;

    @Column(name = "periodo_final")
    private Long periodoFinal;

    @ManyToOne
    @JoinColumn(name = "id_setor")
    private SetorEntity setor;
}
