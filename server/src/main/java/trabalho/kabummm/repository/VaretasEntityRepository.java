package trabalho.kabummm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.VaretasEntity;
import trabalho.kabummm.enums.TipoVaretaEnum;

import java.util.Optional;

public interface VaretasEntityRepository extends JpaRepository<VaretasEntity, Long> {

    Optional<VaretasEntity> findFirstByTipoDaVareta(TipoVaretaEnum tipoDaVareta);
}
