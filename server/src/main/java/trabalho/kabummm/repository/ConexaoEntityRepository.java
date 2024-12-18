package trabalho.kabummm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.ConexaoEntity;

import java.util.List;

public interface ConexaoEntityRepository extends JpaRepository<ConexaoEntity, Long> {
    List<ConexaoEntity> findAllByDependenteId(Long id);

    boolean existsByDependenteIdAndIdProvedor(Long id, Long id1);
}
