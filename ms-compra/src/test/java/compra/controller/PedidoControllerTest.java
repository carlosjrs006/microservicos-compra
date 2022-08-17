package compra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import compra.MsCompraApplication;
import compra.model.Pedido;
import compra.service.PedidoService;
import compra.service.rabbitmq.Producer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MsCompraApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private Producer producer;

    private static final String ROTA_PEDIDO = "/pedido";


    @DisplayName("POST - Deve cadastrar um novo pedido com sucesso no banco de dados")
    @Test
    void deveCadastrarPedidoComSucesso() throws Exception {
        var pedidoBody = getPedido();

        Mockito.doNothing().when(producer).enviarPedido(Mockito.any(Pedido.class));

        mockMvc.perform(post(ROTA_PEDIDO)
                        .content(mapper.writeValueAsString(pedidoBody))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());



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
