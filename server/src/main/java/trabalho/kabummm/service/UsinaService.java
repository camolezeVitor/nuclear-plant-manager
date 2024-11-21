package trabalho.kabummm.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import trabalho.kabummm.entity.SetorEntity;
import trabalho.kabummm.entity.UsinaEntity;
import trabalho.kabummm.entity.VaretasProfundidadeEntity;
import trabalho.kabummm.enums.TipoSecundarioVaretaEnum;
import trabalho.kabummm.enums.TipoVaretaEnum;
import trabalho.kabummm.repository.*;

import java.util.List;

@Service
@AllArgsConstructor
public class UsinaService {
    private final FuncionarioEntityRepository funcionarioEntityRepository;
    private final SetorEntityRepository setorEntityRepository;
    private final UsinaEntityRepository usinaEntityRepository;
    private final VaretasFornecedorEntityRepository varetasFornecedorEntityRepository;
    private final VaretasProfundidadeEntityRepository varetasProfundidadeEntityRepository;
    private final VaretasEntityRepository varetasEntityRepository;

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

    public Double calcularTemperaturaDoReator() {
        Long fatorDeMultiplicacaoDoMaterial = this.varetasFornecedorEntityRepository
                .findByTipoDaVareta(TipoVaretaEnum.VARETA_DE_COMBUSTIVEL)
                .orElseThrow(() -> new RuntimeException("Vareta não encontrada"))
                .getFornecedor().getMaterial().getMultiplicador();
        VaretasProfundidadeEntity varetaProfundidade = this.varetasProfundidadeEntityRepository
                .findById(1L)
                .orElseThrow(() -> new RuntimeException("Vareta não encontrada"));
        Long quantidadeDeVaretasDeCombustivel = this.varetasEntityRepository
                .countByTipoDaVareta(TipoVaretaEnum.VARETA_DE_COMBUSTIVEL);
        Long quantidadeDeVaretasDeMediacao = this.varetasEntityRepository
                .countByTipoDaVareta(TipoVaretaEnum.VARETA_DE_MEDIACAO);
        Long quantidadeDeVaretasDeControleAbsolutas = this.varetasEntityRepository
                .countByTipoSecundario(TipoSecundarioVaretaEnum.VARETA_ABSOLUTA);
        Long quantidadeDeVaretasDeControleParciais = this.varetasEntityRepository
                .countByTipoSecundario(TipoSecundarioVaretaEnum.VARETA_PARCIAL);
        return (double) ((fatorDeMultiplicacaoDoMaterial * quantidadeDeVaretasDeCombustivel) -
                ((fatorDeMultiplicacaoDoMaterial*quantidadeDeVaretasDeControleParciais*(varetaProfundidade.getProfundidadeVaretasDeControleRapido()/100)
                + (fatorDeMultiplicacaoDoMaterial*quantidadeDeVaretasDeControleAbsolutas*(varetaProfundidade.getProfundidadeVaretasDeControleAbsoluto()/100))))
                +(fatorDeMultiplicacaoDoMaterial*quantidadeDeVaretasDeMediacao));
    }

    public Double calcularEnergiaGeradaPeloReator(){
        SetorEntity setorDeTurbina = this.setorEntityRepository
                .findByTipoSetor_CodigoTipoSetor("TURBINA")
                .orElseThrow(()-> new RuntimeException("Setor não encontrado"));
        List<SetorEntity> listaDeSetoresDeBombeamentoDeAgua = this.setorEntityRepository
                .findAllByTipoSetor_CodigoTipoSetor("PRODUCAO_DE_AGUA");
        List<Long> quantidadeDeFuncionariosTrabalhandoNosSetoresDeBombeamentoDeAgua = listaDeSetoresDeBombeamentoDeAgua
                .stream()
                .map(setor -> (long) setor.getFuncionarios().size())
                .toList();
        Long quantidadeDeFuncionariosTrabalhandoNosSetoresDeBombeamentoDeAguaTotal =
                quantidadeDeFuncionariosTrabalhandoNosSetoresDeBombeamentoDeAgua.stream()
                        .mapToLong(Long::longValue).sum();
        Long quantidadeMaximaDeFuncionariosNosSetoresDeBombeamentoDeAgua = listaDeSetoresDeBombeamentoDeAgua
                .stream()
                .map(SetorEntity::getMaximoFuncionarios)
                .mapToLong(Long::longValue)
                .sum();
        Long quantidadeDeAguaProduzida = listaDeSetoresDeBombeamentoDeAgua
                .stream()
                .map(SetorEntity::getQuantidadeItensProduzidos)
                .mapToLong(Long::longValue)
                .sum();

        Integer quantidadeDeFuncionariosTrabalhandonoSetor = setorDeTurbina.getFuncionarios().size();
        Double quantidadeGeradaPeloSetorDeTurbina = (double) ((setorDeTurbina.getQuantidadeItensProduzidos() * quantidadeDeFuncionariosTrabalhandonoSetor)/setorDeTurbina.getMaximoFuncionarios());
        Double quantidadeGeradaPeloSetorDeBombaDeAgua = (double) ((quantidadeDeAguaProduzida * quantidadeDeFuncionariosTrabalhandoNosSetoresDeBombeamentoDeAguaTotal)/quantidadeMaximaDeFuncionariosNosSetoresDeBombeamentoDeAgua);
        Double temperaturaDoReator = this.calcularTemperaturaDoReator();

        Double energiaProduzida = (quantidadeGeradaPeloSetorDeTurbina * quantidadeGeradaPeloSetorDeBombaDeAgua * temperaturaDoReator);
        return null;
    }
}
