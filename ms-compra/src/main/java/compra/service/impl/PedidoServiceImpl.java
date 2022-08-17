package compra.service.impl;

import compra.model.Pedido;
import compra.repository.PedidoRepository;
import compra.service.PedidoService;
import compra.service.rabbitmq.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final Producer producer;

    @Override
    public Pedido salvar(Pedido pedido) {
        pedido = pedidoRepository.save(pedido);
        producer.enviarPedido(pedido);
        return pedido;
    }
}

