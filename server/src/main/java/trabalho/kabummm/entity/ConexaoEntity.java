package trabalho.kabummm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trabalho.kabummm.enums.TipoConexaoEnum;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conexao", schema = "public")
public class ConexaoEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conexao_id_seq")
    @SequenceGenerator(name = "conexao_id_seq", sequenceName = "conexao_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "id_provedor")
    private Long idProvedor;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_dependente")
    private SetorEntity dependente;

    @Column(name = "prioridade")
    private Long prioridade;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conexao")
    private TipoConexaoEnum tipoConexao;
}
