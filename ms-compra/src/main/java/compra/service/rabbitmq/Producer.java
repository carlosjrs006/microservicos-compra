package compra.service.rabbitmq;


import com.fasterxml.jackson.databind.ObjectMapper;
import compra.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Producer {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final ObjectMapper mapper = new ObjectMapper();


    @SneakyThrows
    @PostMapping
    public void enviarPedido(Pedido pedido){
        rabbitTemplate.convertAndSend(queue.getName(), mapper.writeValueAsString(pedido));
    }

}
