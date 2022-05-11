package sa.eti.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.eti.inventoryservice.model.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findBySkuCode(String skuCode);
}
