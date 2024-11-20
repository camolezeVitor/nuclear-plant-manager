package trabalho.kabummm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trabalho.kabummm.entity.UsinaEntity;
import trabalho.kabummm.repository.FuncionarioEntityRepository;
import trabalho.kabummm.repository.SetorEntityRepository;
import trabalho.kabummm.repository.UsinaEntityRepository;

@Service
@AllArgsConstructor
public class UsinaService {

    private final FuncionarioEntityRepository funcionarioEntityRepository;
    private final SetorEntityRepository setorEntityRepository;
    private final UsinaEntityRepository usinaEntityRepository;

    public void descontarNoTotalDaUsinaOSalarioDosFuncionarios() {
        UsinaEntity usina = buscarUsina();
        this.funcionarioEntityRepository.findAll()
                .forEach(funcionario -> {
                    usina.setDinheiro(usina.getDinheiro() - funcionario.getSalario());
                    this.usinaEntityRepository.save(usina);
                });
    }

    public UsinaEntity buscarUsina() {
        return this.usinaEntityRepository
                .findById(1L)
                .orElseThrow(() -> new RuntimeException("Usina não encontrada"));
    }
}
