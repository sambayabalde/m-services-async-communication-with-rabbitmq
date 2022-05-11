package sa.eti.orderservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sa.eti.orderservice.client.InventoryClient;
import sa.eti.orderservice.dto.OrderDto;
import sa.eti.orderservice.model.Order;
import sa.eti.orderservice.repository.OrderRepository;
import sa.eti.orderservice.service.RabbitmqService;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    //private final StreamBridge streamBridge;

    private final RabbitmqService rabbitmqService;

    @PostMapping
    public String placeOrder(@RequestBody OrderDto orderDto) {

        boolean allProductsInStock = orderDto.getOrderLineItemsList().stream()
                .allMatch(orderLineItems -> {
                    //System.out.println(orderLineItems.getSkuCode());
                    return inventoryClient.checkStock(orderLineItems.getSkuCode());
                });

        if (allProductsInStock) {
            Order order = new Order();
            order.setOrderLineItems(orderDto.getOrderLineItemsList());
            order.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(order);
            log.info("Sending Order Details with Order Id {} to Notification Service", order.getId());
            //streamBridge.send("notificationEventSupplier-out-0", MessageBuilder.withPayload(order.getId()).build());
            rabbitmqService.send(order.getId());
            return "Order Place Successfully";
        } else {
            return "Order Failed - One of the Product in your Order is out of stock";
        }
    }
}
