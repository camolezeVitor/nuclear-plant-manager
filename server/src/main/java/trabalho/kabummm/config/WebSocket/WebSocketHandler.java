package trabalho.kabummm.config.WebSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import trabalho.kabummm.dto.webSocket.StatusUsinaFront;
import trabalho.kabummm.enums.StatusReatorEnum;
import trabalho.kabummm.service.UsinaService;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final UsinaService usinaService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Variáveis protegidas contra problemas de concorrência
    private final AtomicLong hora = new AtomicLong(20);
    private final AtomicLong dia = new AtomicLong(6);
    private final AtomicLong mes = new AtomicLong(1);
    private final List<String> mensagem = new CopyOnWriteArrayList<>();
    private final AtomicReference<Double> dinheiro = new AtomicReference<>(0.0);
    private final AtomicReference<Double> energiaProduzidaNoMes = new AtomicReference<>(0.0);
    private final AtomicReference<StatusReatorEnum> status = new AtomicReference<>(StatusReatorEnum.SUBINDO);
    private final AtomicReference<Long> temperaturaReator = new AtomicReference<>(0L);
    private final AtomicReference<String> energiaProduzidaString = new AtomicReference<>("");
    private final AtomicReference<Double> energiaProduzida = new AtomicReference<>(0.0);

    @Autowired
    public WebSocketHandler(UsinaService usinaService) {
        this.usinaService = usinaService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Conexão estabelecida com: " + session.getRemoteAddress());
        Random gerador = new Random();

        this.usinaService.limparHistoricoEnergiaProduzida();
        energiaProduzida.set(this.usinaService.calcularEnergiaGeradaPelasTurbinas());
        dinheiro.set(this.usinaService.buscarUsina().getDinheiro());

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            try {
                if (session.isOpen()) {
                    long horaAtual = hora.incrementAndGet();
                    if (horaAtual == 24) {
                        hora.set(0);
                        dia.incrementAndGet();
                        atualizarStatusReator();
                        salvarHistoricoEnergia();
                    }

                    if (dia.get() % 2 == 0 && hora.get() == 12) {
                        mensagem.clear();
                    }

                    if (dia.get() % 7 == 0 && hora.get() == 12) {
                        this.usinaService.pagarFornecedores();
                        dinheiro.set(this.usinaService.buscarUsina().getDinheiro());
                    }

                    if (dia.get() == 30 && hora.get() == 12) {
                        processarFimDoMes();
                    }

                    if (mes.get() == 13) {
                        mes.set(1);
                    }

                    long temperatura = this.usinaService.calcularTemperaturaReator()
                            .getEnergiaGerada().longValue() + gerador.nextLong(11);
                    temperaturaReator.set(temperatura);

                    enviarStatusParaFront(session);
                }
            } catch (Exception e) {
                System.err.println("Erro ao enviar mensagem para o front: " + e.getMessage());
                e.printStackTrace();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    private synchronized void atualizarStatusReator() {
        double energiaAtual = this.usinaService.calcularEnergiaGeradaPelasTurbinas();
        if (energiaAtual >= energiaProduzida.get()) {
            status.set(StatusReatorEnum.SUBINDO);
        } else {
            status.set(StatusReatorEnum.DESCENDO);
            mensagem.add("A energia produzida está diminuindo!");
        }
        energiaProduzida.set(energiaAtual);
    }

    private synchronized void salvarHistoricoEnergia() {
        String numero = String.format("%.2f", (energiaProduzida.get() / 84600)).replace(",", ".");
        double valor = Double.parseDouble(numero) / 1_000_000;
        energiaProduzidaNoMes.updateAndGet(v -> v + valor);
        energiaProduzidaString.set(String.format("%.2f", valor).replace(",", ".") + " GW/dia");

        this.usinaService.salvarHistoricoEnergiaProduzida(
                dia.get(),
                hora.get(),
                mes.get(),
                energiaProduzidaString.get(),
                String.valueOf(temperaturaReator.get())
        );
    }

    private synchronized void processarFimDoMes() {
        dia.set(1);
        mes.incrementAndGet();

        this.usinaService.receberDinheiroDoGoverno(energiaProduzidaNoMes.get());
        this.usinaService.descontarNoTotalDaUsinaOSalarioDosFuncionarios();

        dinheiro.set(this.usinaService.buscarUsina().getDinheiro());
        energiaProduzidaNoMes.set(0.0);
    }

    private void enviarStatusParaFront(WebSocketSession session) throws Exception {
        StatusUsinaFront statusUsinaFront = new StatusUsinaFront(
                dinheiro.get(),
                status.get(),
                energiaProduzidaString.get(),
                temperaturaReator.get().toString(),
                hora.toString(),
                dia.toString(),
                mensagem
        );

        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(statusUsinaFront)));
    }
}
