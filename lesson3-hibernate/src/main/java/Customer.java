import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "customers_products",
            joinColumns = @JoinColumn(name ="customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productsList;

    public Customer() {
    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProductsCustomer() {
        return productsList;
    }

    public void setProductsCustomer(List<Product> productsList) {
        this.productsList = productsList;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + "}";
    }
}
