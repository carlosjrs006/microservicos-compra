package compra.controller;

import compra.model.Pedido;
import compra.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> salvarPedido(@RequestBody @Valid Pedido pedido){
        return ResponseEntity.ok(pedidoService.salvar(pedido));
    }

}
