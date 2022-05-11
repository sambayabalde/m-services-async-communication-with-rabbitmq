package sa.eti.notification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import sa.eti.notification.Constant.Constant;

@Service
@Slf4j
public class EmailSender {

    @RabbitListener(queues = Constant.QUEUE)
    public void sendEmail(String orderNumber) {
        log.info("Order Placed Successfully - Order Number is {}", orderNumber);
        log.info("Email Sent For Order Id {}", orderNumber);
    }
}
