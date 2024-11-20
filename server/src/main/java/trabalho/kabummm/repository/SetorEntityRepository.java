package trabalho.kabummm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.SetorEntity;

import java.util.Optional;

public interface SetorEntityRepository extends JpaRepository<SetorEntity, Long> {
    Optional<SetorEntity> findByCodigoSetor(String codigoSetor);
}
