package springApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springApp.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceGreaterThan(int minPrice);

    List<Product> findAllByPriceLessThan(int maxPrice);

    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);
}
