package trabalho.kabummm.entity;

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
@Table(name = "users", schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "nome_do_usuario")
    private String nomeDoUsuario;

    @Column(unique = true)
    private String cadastro;

    @Column(name = "senha")
    private String senha;

    @ManyToOne()
    @JoinColumn(name = "id_role")
    private RoleEntity roles;
}
