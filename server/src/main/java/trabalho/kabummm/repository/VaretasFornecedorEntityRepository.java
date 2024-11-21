package trabalho.kabummm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.VaretasFornecedorEntity;
import trabalho.kabummm.enums.TipoVaretaEnum;

import java.util.Optional;

public interface VaretasFornecedorEntityRepository extends JpaRepository<VaretasFornecedorEntity, Long> {
    Optional<VaretasFornecedorEntity> findByTipoDaVareta(TipoVaretaEnum id);
}
