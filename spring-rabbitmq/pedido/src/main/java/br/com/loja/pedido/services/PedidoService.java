package br.com.loja.pedido.services;

import br.com.loja.pedido.entities.Pedido;
import br.com.loja.pedido.enums.StatusPedidoEnum;
import br.com.loja.pedido.repositories.PedidoRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> buscarTodos() {
        return this.pedidoRepository.findAll();
    }

    public Pedido cadastrar(Pedido pedido) {
        pedido.setStatus(StatusPedidoEnum.REALIZADO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setDataAtualizacao(LocalDateTime.now());

        this.pedidoRepository.save(pedido);

        // Se pedido foi cadastrado envia para a fila
        if(pedido.getId() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String payload = objectMapper.writeValueAsString(pedido);
            Message message = new Message((payload).getBytes());
            // Enciando pedido para uma exchange que ira indereçar para as filas ligadas nela
            rabbitTemplate.send("pedidos.ex", "", message);
            return pedido;
        }

        return pedido;
    }

}
