package trabalho.kabummm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trabalho.kabummm.repository.ConexaoEntityRepository;

@Service
@AllArgsConstructor
public class ConexaoService {

    private final ConexaoEntityRepository conexaoEntityRepository;
}
