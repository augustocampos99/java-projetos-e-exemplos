package br.com.loja.pagamento.infra;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoAMQPConfiguration {

    @Bean
    public Queue criarFila() {
        return new Queue("pagamento.processado", false);
    }

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    // Me conectando a exchange de pedidos
    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange("pedidos.ex")
                .build();
    }

    // Criando a fila para receber a mensagem da exchange
    @Bean
    public Queue filaDetalhesPedido() {
        return QueueBuilder
                .nonDurable("pagamento.detalhes-pedido")
                .deadLetterExchange("pedidos.dlx")
                .build();
    }

    // Vinculando a minha fila ao exchange de pedidos para receber as mensagens de pedidos
    @Bean
    public Binding bindPagamentoPedido(FanoutExchange fanoutExchange) {
        return BindingBuilder
                .bind(filaDetalhesPedido())
                .to(fanoutExchange());
    }

    /********************************* DLQs *****************************************/

    // Me conectando a exchange de pedidos DLQ
    @Bean
    public FanoutExchange fanoutExchangeDLQ() {
        return ExchangeBuilder
                .fanoutExchange("pedidos.dlx")
                .build();
    }

    // Criando a fila para receber a mensagem da exchange DLQ
    @Bean
    public Queue filaDetalhesPedidoDLQ() {
        return QueueBuilder
                .nonDurable("pagamento.detalhes-pedido-dlq")
                .build();
    }

    // Vinculando a minha fila ao exchange de pedidos para receber as mensagens de pedidos DLQ
    @Bean
    public Binding bindPagamentoPedidoDLX() {
        return BindingBuilder
                .bind(filaDetalhesPedidoDLQ())
                .to(fanoutExchangeDLQ());
    }


}
