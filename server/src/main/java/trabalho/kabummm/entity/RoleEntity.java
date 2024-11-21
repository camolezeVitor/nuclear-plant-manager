package trabalho.kabummm.entity;

import jakarta.persistence.*;
import lombok.*;
import trabalho.kabummm.enums.RulesEnum;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles", schema = "public")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @SequenceGenerator(name = "roles_id_seq", sequenceName = "roles_id_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RulesEnum role;
}
