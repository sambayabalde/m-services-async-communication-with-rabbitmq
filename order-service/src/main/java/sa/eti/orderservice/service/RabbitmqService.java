package sa.eti.orderservice.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import sa.eti.orderservice.constant.Constant;

@Component
public class RabbitmqService {
    private RabbitTemplate rabbitTemplate;

    public RabbitmqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Object message){
        rabbitTemplate.convertAndSend(Constant.EXCHANGE, Constant.ROUTING_KEY, message);
    }
}
