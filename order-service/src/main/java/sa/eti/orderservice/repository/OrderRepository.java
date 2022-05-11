package sa.eti.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.eti.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
