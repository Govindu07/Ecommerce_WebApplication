package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return " product deleted successfully ";
    }


    @GetMapping("/search")
    public List<Product> search(@RequestParam String name){
        return productService.search(name);
    }

    @GetMapping("/category/{id}")
    public List<Product> filterByCategory(@PathVariable Long id){
        return productService.filterByCategory(id);
    }
    @GetMapping("/price")
    public List<Product> filterByPrice(@RequestParam Double min,@RequestParam Double max){
        return productService.filterByPrice(min,max);
    }
    @GetMapping("/page")
    public Page<Product> getPaginated(@RequestParam int page,@RequestParam int size){
        return productService.getPaginated(page,size);
    }
}
