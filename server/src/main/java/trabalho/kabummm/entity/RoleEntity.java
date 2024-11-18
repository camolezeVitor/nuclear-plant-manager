package trabalho.kabummm.entity;

import jakarta.persistence.*;
import lombok.*;
import trabalho.kabummm.enums.Rules;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles", schema = "public")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Rules role;
}
