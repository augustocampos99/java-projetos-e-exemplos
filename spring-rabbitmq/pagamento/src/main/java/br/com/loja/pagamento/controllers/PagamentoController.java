package br.com.loja.pagamento.controllers;

import br.com.loja.pagamento.services.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("")
    public ResponseEntity buscarTodos() {
        return ResponseEntity.ok(this.pagamentoService.buscarTodos());
    }
}
