package trabalho.kabummm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.FornecedorEntity;
import trabalho.kabummm.entity.MaterialEntity;
import trabalho.kabummm.entity.MedidaEntity;

import java.util.List;

public interface FornecedorEntityRepository extends JpaRepository<FornecedorEntity, Long> {
    List<FornecedorEntity> findAllByMaterialAndMedida(MaterialEntity material, MedidaEntity medida);
    List<FornecedorEntity> findAllByMaterial_TipoMaterial_Codigo(String codigo);
}
