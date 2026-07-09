package com.ecommerce.backend.service;


import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private  final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public Product addProduct(Product product){
        return productRepository.save(product);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new RuntimeException(" Product Not Found "));
    }
    public Product updateProduct(Long id, Product product){
        Product existing=getProductById(id);
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setDescription(product.getDescription());
        existing.setStock(product.getStock());
        existing.setImgUrl(product.getImgUrl());
        existing.setCategory(product.getCategory());
        return productRepository.save(existing);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    public List<Product> search(String name){
        return productRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Product> filterByCategory(Long categoryId){
        return productRepository.findByCategoryId(categoryId);
    }
    public List<Product> filterByPrice(Double min,Double max){
        return  productRepository.findByPriceBetween(min,max);
    }
    public Page<Product> getPaginated(int page, int size){
        return productRepository.findAll(PageRequest.of(page,size));
    }
}
