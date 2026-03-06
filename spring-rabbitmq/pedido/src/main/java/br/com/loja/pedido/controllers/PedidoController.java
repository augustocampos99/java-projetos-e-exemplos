package br.com.loja.pedido.controllers;

import br.com.loja.pedido.entities.Pedido;
import br.com.loja.pedido.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping()
    public ResponseEntity buscarTodos() {
        return ResponseEntity.ok(this.pedidoService.buscarTodos());
    }

    @PostMapping()
    public ResponseEntity cadastrar(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(this.pedidoService.cadastrar(pedido));
    }

}
