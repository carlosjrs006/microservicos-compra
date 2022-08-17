package compra.service.impl;

import compra.model.Pedido;
import compra.repository.PedidoRepository;
import compra.service.rabbitmq.Producer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceImplTest {

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Mock
    private  PedidoRepository pedidoRepository;

    @Mock
    private  Producer producer;

    @DisplayName("Salvar pedido com sucesso")
    @Test
    void deveSalvarUmPedidoComSucesso(){
        var pedidoMok = getPedido();
        Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(pedidoMok);
        Mockito.doNothing().when(producer).enviarPedido(Mockito.any(Pedido.class));
        Pedido pedidoSalvo = pedidoService.salvar(pedidoMok);

        Assertions.assertEquals(pedidoMok.getCep(), pedidoSalvo.getCep());
        Assertions.assertNotNull(pedidoSalvo.getCep());

    }

    Pedido getPedido(){
        return Pedido.builder()
                .cep("72593224")
                .cpfCliente("07375900175")
                .dataCompra(new Date())
                .email("carlosjrs06.cj@gmail.com")
                .nome("carlos junio")
                .produto(4L)
                .valor(new BigDecimal(1000))
                .build();
    }

}
