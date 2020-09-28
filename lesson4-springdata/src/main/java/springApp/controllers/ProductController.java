package springApp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springApp.models.Product;
import springApp.repositories.specifications.ProductSpecification;
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
                                  @RequestParam(name = "max_price", required = false) Integer maxPrice,
                                  @RequestParam(name = "title", required = false) String title,
                                  @RequestParam(name = "id", required = false) Long id,
                                  @RequestParam(name = "page", defaultValue = "1") Integer pageNumber) {
        logger.info("Filtering by min_price: {}", minPrice);
        Specification<Product> specification = Specification.where(null);
        if (minPrice != null) {
            specification = specification.and(ProductSpecification.priceGEThan(minPrice));
            logger.info("minPrice");
        }
        if (maxPrice != null) {
            specification = specification.and(ProductSpecification.priceLEThan(maxPrice));
            logger.info("maxPrice");
        }
        if (title != null) {
            specification = specification.and(ProductSpecification.titleLike(title));
        }
        if (id != null) {
            specification = specification.and(ProductSpecification.idE(id));
        }

        Page<Product> productsList = productService.findAll(specification, pageNumber);
        model.addAttribute("products", productsList);
        return "products";

    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
//        Specification<Product> specification = Specification.where(null);
//        List<Product> productList = productService.findAll(specification);
//        model.addAttribute("product", productList);
//        return "edit_product_form";
        model.addAttribute("product", productService.findById(id));
        return "edit_product_form";
    }

    @PostMapping("/edit")
    public String updateProduct(@ModelAttribute Product productModify) {
        productService.updateProduct(productModify);
        return "redirect:/products/";
    }
}