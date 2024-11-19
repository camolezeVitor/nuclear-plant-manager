package trabalho.kabummm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.FuncionarioEntity;

public interface FuncionarioEntityRepository extends JpaRepository<FuncionarioEntity, Long> {
}
