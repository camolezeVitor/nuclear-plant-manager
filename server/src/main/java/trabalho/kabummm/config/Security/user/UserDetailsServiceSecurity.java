package trabalho.kabummm.config.Security.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trabalho.kabummm.entity.UserEntity;
import trabalho.kabummm.repository.UserEntityRepository;

@Service
@AllArgsConstructor
public class UserDetailsServiceSecurity implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String cadastro) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByCadastro(cadastro)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
        return new UserDetailsImpl(userEntity);
    }
}
