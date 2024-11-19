package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.fornecedor.FornecedorDto;
import trabalho.kabummm.entity.FornecedorEntity;
import trabalho.kabummm.repository.FornecedorEntityRepository;
import trabalho.kabummm.repository.MaterialEntityRepository;
import trabalho.kabummm.repository.MedidaEntityRepository;
import trabalho.kabummm.request.fornecedor.CriarFornecedorRequest;

import java.util.List;

@Service
@AllArgsConstructor
public class FornecedorService {

    private final FornecedorEntityRepository fornecedorEntityRepository;
    private final MaterialEntityRepository materialEntityRepository;
    private final MedidaEntityRepository medidaEntityRepository;

    public ResponseEntity<List<FornecedorDto>> buscarTodosFornecedores() {
        List<FornecedorEntity> fornecedores = this.fornecedorEntityRepository.findAll();
        if(fornecedores.isEmpty())throw new RuntimeException("Nenhum fornecedor encontrado");
        return ResponseEntity.ok(FornecedorDto.converter(fornecedores));
    }

    public ResponseEntity<FornecedorDto> buscarFornecedorPorId(Long id) {
        FornecedorEntity fornecedor = this.fornecedorEntityRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Fornecedor com id"+ id +"não encontrado"));
        return ResponseEntity.ok(new FornecedorDto(fornecedor));
    }

    @Transactional
    public void cadastrarFornecedor(CriarFornecedorRequest criarFornecedorRequest) {
        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setNome(criarFornecedorRequest.getNome());
        fornecedor.setQuantidadeItensFornecedor(criarFornecedorRequest.getQuantidadeItensFornecedor());
        fornecedor.setPreco(criarFornecedorRequest.getPreco());
        fornecedor.setMaterial(this.materialEntityRepository
                .findById(criarFornecedorRequest.getIdMaterial())
                .orElseThrow(() -> new RuntimeException("Material com id"+ criarFornecedorRequest.getIdMaterial() +"não encontrado")));
        fornecedor.setMedida(this.medidaEntityRepository
                .findById(criarFornecedorRequest.getIdMedida())
                .orElseThrow(() -> new RuntimeException("Medida com id"+ criarFornecedorRequest.getIdMedida() +"não encontrada")));


    }
}
