package trabalho.kabummm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.RoleEntity;
import trabalho.kabummm.enums.RulesEnum;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByRole(RulesEnum role);
}
