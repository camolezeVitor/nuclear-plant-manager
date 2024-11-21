package trabalho.kabummm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.MaterialEntity;
import trabalho.kabummm.entity.MedidaEntity;
import trabalho.kabummm.entity.SetorEntity;

import java.util.List;
import java.util.Optional;

public interface SetorEntityRepository extends JpaRepository<SetorEntity, Long> {
    Optional<SetorEntity> findByCodigoSetor(String codigoSetor);
    Optional<SetorEntity> findByTipoSetor_CodigoTipoSetor(String tipoDoSetor);
    List<SetorEntity> findAllByTipoSetor_CodigoTipoSetor(String tipoDoSetor);
    List<SetorEntity> findAllByMaterialAndMedida(MaterialEntity material, MedidaEntity medida);
}
