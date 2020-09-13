package springApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springApp.model.Product;
import springApp.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_product";
    }

    @PostMapping("/add")
    public String addNewProduct(@ModelAttribute Product newProduct) {
        productService.addNewProduct(newProduct);
        return "redirect:/products/";
    }

    @GetMapping("/find_by_id")
    public String showFindByIdForm() {
        return "find_product_by_id";
    }

    @PostMapping("/find_by_id")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        System.out.println(id);
        return "product";
    }
}