package com.developer.marketspring.domain.service;

import com.developer.marketspring.domain.Eschema.Product;
import com.developer.marketspring.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// La clase servicio sirve como intermediador entre la capa del repositorio y el controlador

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }
/*
    public Optional<List<Product>> getScarseProducts(int quantity){
        return productRepository.getScarseProducts(quantity);
    }
*/
    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    // ***********************************************

    public Product updateProduct(Product product, int id){
        Product updProduct = getProduct(id).get();
            updProduct.setName(product.getName());
            updProduct.setCategory(product.getCategory());
            updProduct.setPrice(product.getPrice());
            updProduct.setStock(product.getStock());
            updProduct.setActive(product.isActive());
            return productRepository.save(updProduct);
    }

    // ***********************************************
/*
    public Product updateProduct(Product product, int id){
        return getProduct(id).map(updProduct -> {
            updProduct.setName(product.getName());
            updProduct.setCategoryId(product.getCategoryId());
            updProduct.setPrice(product.getPrice());
            updProduct.setStock(product.getStock());
            updProduct.setActive(product.isActive());
            return productRepository.updateProduct(updProduct);
        }).orElseGet(() -> {
            product.setProductId(id);
                    return save(product);
        });
    }

    public void delete(int productId){
        productRepository.delete(productId);
    }

    public boolean delete(int productId){
        if (getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return true; // Si el producto existe
        } else {
            return false; // Si el producto NO existe
        }
    }
*/
    public boolean delete(int productId){
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true; // Si el producto existe
        }).orElse(false); // Si el producto NO existe
    }

}
