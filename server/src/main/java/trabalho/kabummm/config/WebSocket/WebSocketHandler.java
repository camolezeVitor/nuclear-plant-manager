package trabalho.kabummm.config.WebSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import trabalho.kabummm.dto.webSocket.StatusUsinaFront;
import trabalho.kabummm.entity.UsinaEntity;
import trabalho.kabummm.enums.StatusReatorEnum;
import trabalho.kabummm.service.UsinaService;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UsinaService usinaService;

    private Long hora = 0L;
    private Long dia = 1L;
    private Double dinheiro = 0.0;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("Conexão estabelecida com: " + session.getRemoteAddress());

        AtomicReference<UsinaEntity> usinaSelecionada = new AtomicReference<>(this.usinaService.buscarUsina());
        dinheiro = usinaSelecionada.get().getDinheiro();

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            try {
                if (session.isOpen()) {
                    hora++;
                    if(hora == 24) {
                        hora = 0L;
                        dia++;
                    }

                    if(dia == 30) {
                        dia = 1L;
                        this.usinaService.descontarNoTotalDaUsinaOSalarioDosFuncionarios();
                        usinaSelecionada.set(this.usinaService.buscarUsina());
                        dinheiro = usinaSelecionada.get().getDinheiro();
                    }

                    StatusUsinaFront statusUsinaFront =
                            new StatusUsinaFront(
                                    dinheiro,
                                    StatusReatorEnum.SUBINDO,
                                    "1000000",
                                    "0",
                                    hora.toString(),
                                    dia.toString(),
                                    "O MERCADO SÃO JUDAS TADEU ESTÁ COM PROMOÇÕES IMPERDIVEIS NESSE FERIADO!!" );
                    session.sendMessage(new TextMessage(objectMapper.writeValueAsString(statusUsinaFront)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
