package trabalho.kabummm.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fornecedor", schema = "public")
public class FornecedorEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantidade_itens_fornecedor")
    private Long quantidadeItensFornecedor;

    @Column(name = "preco")
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private MaterialEntity material;

    @JoinColumn(name = "id_medida")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private MedidaEntity medida;
}
