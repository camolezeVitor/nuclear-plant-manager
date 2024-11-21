package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.entity.ConexaoEntity;
import trabalho.kabummm.entity.SetorEntity;
import trabalho.kabummm.enums.TipoConexaoEnum;
import trabalho.kabummm.repository.ConexaoEntityRepository;
import trabalho.kabummm.repository.SetorEntityRepository;
import trabalho.kabummm.request.conexao.ConexaoRequest;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ConexaoService {

    private final ConexaoEntityRepository conexaoEntityRepository;
    private final SetorEntityRepository setorEntityRepository;

    @Transactional
    public ResponseEntity<HttpStatus> criarConexaoEntreSetores(ConexaoRequest conexaoRequest) {
        ConexaoEntity conexaoEntity = new ConexaoEntity();
        SetorEntity dependente = this.setorEntityRepository.findById(conexaoRequest.getIdDependente())
                .orElseThrow(() -> new RuntimeException("Setor dependente não encontrado"));
        conexaoEntity.setDependente(dependente);

        List<ConexaoEntity> listaConexoes = this.conexaoEntityRepository.findAllByDependenteId(conexaoRequest.getIdDependente());
        listaConexoes.forEach(conexao -> {
            if (Objects.equals(conexao.getPrioridade(), conexaoRequest.getPrioridade())) {
                throw new RuntimeException("Já existe uma conexão com essa prioridade");
            }
        });
        conexaoEntity.setPrioridade(conexaoRequest.getPrioridade());
        conexaoEntity.setIdProvedor(conexaoRequest.getIdProvedor());
        conexaoEntity.setTipoConexao(TipoConexaoEnum.valueOf(conexaoRequest.getTipoConexao()));
        this.conexaoEntityRepository.save(conexaoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity<HttpStatus> deletarConexao(Long id) {
        ConexaoEntity conexaoEntity = this.conexaoEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conexão não encontrada"));
        this.conexaoEntityRepository.delete(conexaoEntity);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
