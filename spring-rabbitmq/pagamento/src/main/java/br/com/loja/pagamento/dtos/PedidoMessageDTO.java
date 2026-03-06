package br.com.loja.pagamento.dtos;

import br.com.loja.pagamento.enums.TipoPagamentoEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoMessageDTO(
        UUID id,
        String cpfCliente,
        Double valor,
        TipoPagamentoEnum tipoPagamento,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) { }
