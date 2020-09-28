package springApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import springApp.models.Product;
import springApp.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(Specification<Product> specification) {
        return productRepository.findAll(specification);
    }

    public  Product findById(Long id){
        return productRepository.findOneById(id);
    }


    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }


    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if (page < 1) {
            page = 1;
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

}