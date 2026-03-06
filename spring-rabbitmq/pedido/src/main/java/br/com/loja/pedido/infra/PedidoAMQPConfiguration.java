package br.com.loja.pedido.infra;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoAMQPConfiguration {

    @Bean
    public RabbitAdmin criaRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> inicializaAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }

    // Preciso criar uma exchange do tipo Fannout para replicar para todas as filas ligadas nela
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("pedidos.ex");
    }

    // Exchange for DLQ
    @Bean
    public FanoutExchange fanoutDLQExchange() {
        return new FanoutExchange("pedidos.dlx");
    }

}
