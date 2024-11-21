package trabalho.kabummm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "varetas_profundidade")
public class VaretasProfundidadeEntity {

    @Id
    private Long id;

    @Column(name = "profundida_varetas_rapido")
    private Long profundidadeVaretasDeControleRapido;

    @Column(name = "profundida_varetas_absoluto")
    private Long profundidadeVaretasDeControleAbsoluto;
}
