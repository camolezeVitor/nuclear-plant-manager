package trabalho.kabummm.service;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trabalho.kabummm.dto.energiaProducaoHistoricoDto.EnergiaProducaoHistoricoDto;
import trabalho.kabummm.dto.reator.UsinaMensagensDto;
import trabalho.kabummm.entity.*;
import trabalho.kabummm.enums.TipoSecundarioVaretaEnum;
import trabalho.kabummm.enums.TipoVaretaEnum;
import trabalho.kabummm.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsinaService {
    private final FuncionarioEntityRepository funcionarioEntityRepository;
    private final EnergiaProducaoHistoricoEntityRepository energiaProducaoHistoricoEntityRepository;
    private final SetorEntityRepository setorEntityRepository;
    private final UsinaEntityRepository usinaEntityRepository;
    private final FornecedorEntityRepository fornecedorEntityRepository;
    private final VaretasFornecedorEntityRepository varetasFornecedorEntityRepository;
    private final VaretasProfundidadeEntityRepository varetasProfundidadeEntityRepository;
    private final VaretasEntityRepository varetasEntityRepository;

    public UsinaService( FuncionarioEntityRepository funcionarioEntityRepository, EnergiaProducaoHistoricoEntityRepository energiaProducaoHistoricoEntityRepository, SetorEntityRepository setorEntityRepository, UsinaEntityRepository usinaEntityRepository, FornecedorEntityRepository fornecedorEntityRepository, VaretasFornecedorEntityRepository varetasFornecedorEntityRepository, VaretasProfundidadeEntityRepository varetasProfundidadeEntityRepository, VaretasEntityRepository varetasEntityRepository) {
        this.funcionarioEntityRepository = funcionarioEntityRepository;
        this.energiaProducaoHistoricoEntityRepository = energiaProducaoHistoricoEntityRepository;
        this.setorEntityRepository = setorEntityRepository;
        this.usinaEntityRepository = usinaEntityRepository;
        this.fornecedorEntityRepository = fornecedorEntityRepository;
        this.varetasFornecedorEntityRepository = varetasFornecedorEntityRepository;
        this.varetasProfundidadeEntityRepository = varetasProfundidadeEntityRepository;
        this.varetasEntityRepository = varetasEntityRepository;
    }

    public List<String> mensagens = new ArrayList<>();

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

    @Transactional
    public UsinaMensagensDto calcularTemperaturaReator() {

        Optional<VaretasFornecedorEntity> optCoeficienteVaretasCombustivel =  this.varetasFornecedorEntityRepository
                .findByTipoDaVareta(TipoVaretaEnum.VARETA_DE_COMBUSTIVEL);

        if(optCoeficienteVaretasCombustivel.isEmpty()){
            this.mensagens.add("Nenhum fornecedor de vareta de combustivel encontrado!!");
        }
        Long coeficienteVaretasCombustivel = optCoeficienteVaretasCombustivel.get().getFornecedor().getMaterial().getMultiplicador();

        VaretasProfundidadeEntity profundidade = this.varetasProfundidadeEntityRepository
                .findById(1L)
                .orElseThrow();

        Long profundidadeVaretaAbsolutas = profundidade.getProfundidadeVaretasDeControleAbsoluto();
        Long profundidadeVaretaRapidas = profundidade.getProfundidadeVaretasDeControleRapido();

        Double coeficienteVaretasDeControleAbsoluto = 1D;
        Double coeficienteVaretasDeControleRapido = 1D;
        Double coefiecienteVaretasModeradores = 5D;

        Long qtdCombustivel = this.varetasEntityRepository.countByTipoDaVareta(TipoVaretaEnum.VARETA_DE_COMBUSTIVEL);
        Long qtdModeradoras = this.varetasEntityRepository.countByTipoDaVareta(TipoVaretaEnum.VARETA_DE_MEDIACAO);
        Long qtdControleParcial = this.varetasEntityRepository.countByTipoSecundario(TipoSecundarioVaretaEnum.VARETA_PARCIAL);
        Long qtdControleAbsoluto = this.varetasEntityRepository.countByTipoSecundario(TipoSecundarioVaretaEnum.VARETA_ABSOLUTA);

        if(qtdModeradoras == 0 || qtdControleParcial == 0 || qtdControleAbsoluto == 0){
            this.mensagens.add("Nenhuma vareta de combustivel detectada!! Adicione Imediatamente!!");
        }

        Double temperatura = ((((qtdCombustivel * coeficienteVaretasCombustivel)
                        + Math.pow(qtdModeradoras * coefiecienteVaretasModeradores, 2)) - 370)
                        * 0.1
                        * ((1 - (((profundidadeVaretaAbsolutas / 2.0)
                        + (profundidadeVaretaRapidas / 2.0)) / 100.0))
                        / (qtdControleAbsoluto + qtdControleParcial))
                        * 10 / (coeficienteVaretasDeControleAbsoluto + coeficienteVaretasDeControleRapido)
                        + 100);

        if(temperatura > 400){
            this.mensagens.add("Reator superaquecido!! Temperatura critica!!");
        }
        return new UsinaMensagensDto(temperatura, mensagens);
    }

    @Transactional
    public Double calcularEnergiaGeradaPelasTurbinas(){
        Optional<SetorEntity> optSetor = this.setorEntityRepository
                .findByTipoSetor_CodigoTipoSetor("TURBINA");
        if(optSetor.isEmpty()){
            this.mensagens.add("Nenhum setor de turbina encontrado!!");
        } else {
            SetorEntity setorDeTurbina = optSetor.get();

            List<SetorEntity> listaDeSetoresDeBombeamentoDeAgua = this.setorEntityRepository
                    .findAllByTipoSetor_CodigoTipoSetor("PRODUCAO_DE_AGUA");
            if(listaDeSetoresDeBombeamentoDeAgua.size() < 2){
                this.mensagens.add("Poucos ou nenhum setor de bombeamento de água encontrado!!");
            }

            if(!listaDeSetoresDeBombeamentoDeAgua.isEmpty()){
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
                Double temperaturaDoReator = this.calcularTemperaturaReator().getEnergiaGerada();

                return (quantidadeGeradaPeloSetorDeTurbina * quantidadeGeradaPeloSetorDeBombaDeAgua * temperaturaDoReator);
            }
        }

        return 0.0;
    }

    public void pagarFornecedores() {
        List<FornecedorEntity> fornecedores = this.fornecedorEntityRepository.findAll();
        UsinaEntity usina = buscarUsina();
        fornecedores.forEach(fornecedor -> {
            if(usina.getDinheiro() < fornecedor.getPreco()){
               this.mensagens.add("Dinheiro insuficiente para pagar fornecedor: " + fornecedor.getNome());
                usina.setDinheiro(usina.getDinheiro() - fornecedor.getPreco());
            }
            if(usina.getDinheiro() < 50000){
                this.mensagens.add("Beirando a falencia, cuidado!!");
            }
            this.usinaEntityRepository.save(usina);
        });
        this.usinaEntityRepository.save(usina);
    }

    public void salvarHistoricoEnergiaProduzida(Long dia, Long hora, Long mes, String valor, String temperatura) {
        EnergiaProducaoHistoricoEntity energiaProducaoHistorico = new EnergiaProducaoHistoricoEntity();
        energiaProducaoHistorico.setEnergiaGerada(valor);
        energiaProducaoHistorico.setDataProducao(dia + "/" + mes + " ás " + hora + "h");
        energiaProducaoHistorico.setTemperatura(temperatura);
        this.energiaProducaoHistoricoEntityRepository.save(energiaProducaoHistorico);
    }

    public void limparHistoricoEnergiaProduzida() {
        this.energiaProducaoHistoricoEntityRepository.deleteAll();
    }

    public ResponseEntity<List<EnergiaProducaoHistoricoDto>> buscarRegistros() {
        List<EnergiaProducaoHistoricoEntity> energiaProducaoHistorico = this.energiaProducaoHistoricoEntityRepository.findAll();
        return ResponseEntity.ok(EnergiaProducaoHistoricoDto.converter(energiaProducaoHistorico));
    }

    public void receberDinheiroDoGoverno(Double energiaProduzidaNoMes) {
        UsinaEntity usina = buscarUsina();
        usina.setDinheiro(usina.getDinheiro() + energiaProduzidaNoMes*100000);
        this.usinaEntityRepository.save(usina);
    }
}
