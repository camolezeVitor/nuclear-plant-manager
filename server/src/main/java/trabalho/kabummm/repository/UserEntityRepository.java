package trabalho.kabummm.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import trabalho.kabummm.entity.UserEntity;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByCadastro(String cadastro);
    Boolean existsByCadastro(String cadastro);
    void deleteByCadastro(String cadastro);
}
