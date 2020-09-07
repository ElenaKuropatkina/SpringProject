package springApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.model.Product;
import springApp.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {

        return productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addNewProduct(Product product) {

        return productRepository.addNewProduct(product);
    }

}
