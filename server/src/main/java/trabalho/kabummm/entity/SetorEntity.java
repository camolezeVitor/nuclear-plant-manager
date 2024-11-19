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
@Table(name = "setor", schema = "public")
public class SetorEntity {

    @Id
    private Long id;

    @Column(name = "codigo_setor")
    private String codigo_setor;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade_itens_produzidos")
    private Long quantidadeItensProduzidos;

    @Column(name = "maximo_funcionarios")
    private Long maximoFuncionarios;

    @ManyToOne
    @JoinColumn(name = "id_medida")
    private MedidaEntity medida;

    @ManyToOne
    @JoinColumn(name = "id_tipo_setor")
    private TipoSetorEntity tipoSetor;
}
