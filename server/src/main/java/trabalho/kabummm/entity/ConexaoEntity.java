package trabalho.kabummm.entity;

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

    @OneToOne
    @JoinColumn(name = "id_provedor")
    private SetorEntity provedor;

    @OneToOne
    @JoinColumn(name = "id_dependente")
    private SetorEntity dependente;

    @Column(name = "prioridade")
    private Long prioridade;

    @Column(name = "tipo_conexao")
    @Enumerated(EnumType.STRING)
    private TipoConexaoEnum tipoConexao;
}
