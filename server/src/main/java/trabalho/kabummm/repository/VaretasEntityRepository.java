package trabalho.kabummm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.VaretasEntity;
import trabalho.kabummm.enums.TipoSecundarioVaretaEnum;
import trabalho.kabummm.enums.TipoVaretaEnum;


public interface VaretasEntityRepository extends JpaRepository<VaretasEntity, Long> {

    Long countByTipoDaVareta(TipoVaretaEnum tipoVaretaEnum);
    Long countByTipoSecundario(TipoSecundarioVaretaEnum tipoVaretaEnum);
}
