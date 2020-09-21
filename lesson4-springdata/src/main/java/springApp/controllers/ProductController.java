package springApp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springApp.models.Product;
import springApp.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private final static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showAllProducts(Model model, @RequestParam(name = "min_price", required = false) Integer minPrice,
                                  @RequestParam(name = "max_price", required = false) Integer maxPrice) {
        logger.info("Filtering by min_price: {}", minPrice);
        List<Product> productsList;
        if (minPrice != null && maxPrice == null) {
            productsList = productService.findByMinPrice(minPrice);
            logger.info("minPrice");
        } else if (maxPrice != null && minPrice == null) {
            productsList = productService.findByMaxPrice(maxPrice);
            logger.info("maxPrice");
        } else if (maxPrice != null && minPrice != null) {
            productsList = productService.find(minPrice, maxPrice);
            logger.info("Between");
        } else {
            productsList = productService.findAllProducts();
            logger.info("All");
        }

        model.addAttribute("products", productsList);
        return "products";

    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute Product productModify) {
        productService.updateProduct(productModify);
        return "redirect:/products/";
    }
}
