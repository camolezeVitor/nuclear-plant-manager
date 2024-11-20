package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.fornecedor.FornecedorDto;
import trabalho.kabummm.entity.FornecedorEntity;
import trabalho.kabummm.repository.FornecedorEntityRepository;
import trabalho.kabummm.repository.MaterialEntityRepository;
import trabalho.kabummm.repository.MedidaEntityRepository;
import trabalho.kabummm.request.fornecedor.FornecedorRequest;

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
                .orElseThrow(()-> new RuntimeException("Fornecedor com id "+ id +" não encontrado"));
        return ResponseEntity.ok(new FornecedorDto(fornecedor));
    }

    @Transactional
    public ResponseEntity<HttpStatus> cadastrarFornecedor(FornecedorRequest fornecedorRequest) {
        FornecedorEntity fornecedor = new FornecedorEntity();
        fornecedor.setNome(fornecedorRequest.getNome());
        fornecedor.setQuantidadeItensFornecedor(fornecedorRequest.getQuantidadeItensFornecedor());
        fornecedor.setPreco(fornecedorRequest.getPreco());
        fornecedor.setMaterial(this.materialEntityRepository
                .findById(fornecedorRequest.getIdMaterial())
                .orElseThrow(() -> new
                        RuntimeException("Material com id "+ fornecedorRequest.getIdMaterial() +" não encontrado")));
        fornecedor.setMedida(this.medidaEntityRepository
                .findById(fornecedorRequest.getIdMedida())
                .orElseThrow(() -> new
                        RuntimeException("Medida com id "+ fornecedorRequest.getIdMedida() +" não encontrada")));
        this.fornecedorEntityRepository.save(fornecedor);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<HttpStatus> atualizarFornecedor(Long id, FornecedorRequest fornecedorRequest) {
        FornecedorEntity fornecedor = this.fornecedorEntityRepository.findById(id)
                .orElseThrow(()-> new
                        RuntimeException("Fornecedor com id "+ id +" não encontrado"));
        fornecedor.setNome(fornecedorRequest.getNome());
        fornecedor.setQuantidadeItensFornecedor(fornecedorRequest.getQuantidadeItensFornecedor());
        fornecedor.setPreco(fornecedorRequest.getPreco());
        fornecedor.setMaterial(this.materialEntityRepository
                .findById(fornecedorRequest.getIdMaterial())
                .orElseThrow(() -> new
                        RuntimeException("Material com id "+ fornecedorRequest.getIdMaterial() +" não encontrado")));
        fornecedor.setMedida(this.medidaEntityRepository
                .findById(fornecedorRequest.getIdMedida())
                .orElseThrow(() -> new
                        RuntimeException("Medida com id "+ fornecedorRequest.getIdMedida() +" não encontrada")));
        this.fornecedorEntityRepository.save(fornecedor);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity<HttpStatus> deletarFornecedor(Long id) {
        if(!this.fornecedorEntityRepository.existsById(id))throw new RuntimeException("Fornecedor com id "+ id +" não encontrado");
        this.fornecedorEntityRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
