package springApp.repositories;

import org.springframework.stereotype.Component;
import springApp.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {

    private List<Product> productList;

    @PostConstruct
    public void init() {
        this.productList = new ArrayList<>();
        productList.add(new Product(1L, "MacBook Air 13", 700));
        productList.add(new Product(2L, "MacBook Air 15", 900));
        productList.add(new Product(3L, "IPad Air", 300));

    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(productList);
    }

    public Product findById(Long id) {
        for (Product product : productList) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        throw new RuntimeException("Product not found");
    }

    public Product addNewProduct(Product product) {
        productList.add(product);
        return product;
    }
}

