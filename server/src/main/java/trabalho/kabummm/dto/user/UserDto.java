package trabalho.kabummm.dto.user;

import lombok.Getter;
import trabalho.kabummm.entity.RoleEntity;
import trabalho.kabummm.entity.UserEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserDto {
    private final Long id;
    private final String nomeDoUsuario;
    private final String cadastro;
    private final String role;

    public UserDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.nomeDoUsuario = userEntity.getNomeDoUsuario();
        this.cadastro = userEntity.getCadastro();
        this.role = userEntity.getRoles().getRole().name();
    }

    public static List<UserDto> convert(List<UserEntity> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }
}
