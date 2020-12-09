package com.developer.marketspring.domain.repository;

import com.developer.marketspring.domain.Eschema.Product;

import java.util.List;
import java.util.Optional;

/*
 * Reglas de nuestro dominio. En el momento en que cualquier
 * repositorio quiera utilizar o acceder productos de nuestra base de datos
 */
public interface ProductRepository {

    List<Product> getAll();

    Optional<List<Product>> getByCategory(int categoryId);

    Optional<List<Product>> getScarseProducts(int quantity);

    Optional<Product> getProduct(int productId);

    Product save(Product product);

    // ***********************************************

    //Product updateProduct(Product product);

    // ***********************************************

    void delete(int productId);

}
