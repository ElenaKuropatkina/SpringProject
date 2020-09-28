package springApp.repositories;


        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
        import org.springframework.stereotype.Repository;
        import springApp.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Product findOneByTitle(String title);

    Product findOneById(Long id);

}