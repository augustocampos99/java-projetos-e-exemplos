package br.com.loja.pagamento.services;

import br.com.loja.pagamento.entities.Pagamento;
import br.com.loja.pagamento.repositories.PagamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    public List<Pagamento> buscarTodos() {
        return this.pagamentoRepository.findAll();
    }

    public Pagamento cadastrar(Pagamento pagamento) {
        pagamento.setDataProcessamento(LocalDateTime.now());
        this.pagamentoRepository.save(pagamento);
        return pagamento;
    }
}
