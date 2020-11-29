package com.developer.marketspring.web.controller;

import com.developer.marketspring.domain.Product;
import com.developer.marketspring.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Esta clase es el controlador de la API rest
@RequestMapping("/products") // Lleva como parametro el path al que se hacen las peticiones
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/category/{id}")
    public Optional<List<Product>> getByCategory(@PathVariable("id") int categoryId){
        return productService.getByCategory(categoryId);
    }
/*
    public Optional<List<Product>> getScarseProducts(int quantity){
        return productService.getScarseProducts(quantity);
    }
*/
    @GetMapping("/{id}")
    public Optional<Product> getProduct(@PathVariable("id") int productId){
        return productService.getProduct(productId);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id") int id) {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId){
        return productService.delete(productId);
    }
}
