package sa.eti.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sa.eti.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
