package br.com.loja.pagamento.amqp;

import br.com.loja.pagamento.dtos.PedidoMessageDTO;
import br.com.loja.pagamento.entities.Pagamento;
import br.com.loja.pagamento.enums.StatusPagamentoEnum;
import br.com.loja.pagamento.enums.TipoPagamentoEnum;
import br.com.loja.pagamento.services.PagamentoService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

@Component
public class PedidoListener {

    private final PagamentoService pagamentoService;

    public PedidoListener(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    // Essa mansagem vai chegar pela exchange que vai mandar para essa e outras filas
    @RabbitListener(queues = "pagamento.detalhes-pedido")
    public void consumirPedidoRealizado(Message message) {
        byte[] body = message.getBody();
        String messageBody = new String(body, StandardCharsets.UTF_8);
        System.out.println(messageBody);

        ObjectMapper objectMapper = new ObjectMapper();
        PedidoMessageDTO pedido = objectMapper.readValue(messageBody, PedidoMessageDTO.class);

        // Simulando erro para mandar a mensagem para DLQ
        if(pedido.valor() == 0) {
            throw new RuntimeException("Erro no valor. Mensagem enviada para o DLQ ;)");
        }

        var pagamento = new Pagamento();
        pagamento.setIdPedido(pedido.id());
        pagamento.setTipoPagamento(pedido.tipoPagamento());
        pagamento.setValor(pedido.valor());

        // Se for boleto mando cadastro como pagamento NEGADO para teste
        if(pedido.tipoPagamento() == TipoPagamentoEnum.BOLETO) {
            pagamento.setStatus(StatusPagamentoEnum.NEGADO);
        }
        else {
            pagamento.setStatus(StatusPagamentoEnum.CONFIRMADO);
        }

        pagamentoService.cadastrar(pagamento);
    }

}
