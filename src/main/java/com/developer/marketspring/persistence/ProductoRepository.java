package com.developer.marketspring.persistence;

import com.developer.marketspring.domain.Product;
import com.developer.marketspring.domain.repository.ProductRepository;
import com.developer.marketspring.persistence.crud.ProductoCrudRepository;
import com.developer.marketspring.persistence.entity.Producto;
import com.developer.marketspring.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Repositorio orientado al dominio
@Repository // Indicamos a spring que esta clase se encarga de interactuar con la BD
public class ProductoRepository implements ProductRepository {

    // Inyeccion de dependencias
    @Autowired // con Autowired le cedemos el control a spring para crear las instancias de nuestros objetos
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper mapper;

    /* Método para obtener toda la lista de productos que necesito */
    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    /* Método para obtener la lista de productos por idCategoria*/
    @Override
    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    /* Metodo para obtener los productos escasos*/
    @Override
    public Optional<List<Product>> getScarseProducts(int quantity){
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(pdts -> mapper.toProducts(pdts));
    }

    /* Consultar un producto en particular */
    @Override
    public Optional<Product> getProduct(int idProducto){
        return productoCrudRepository.findById(idProducto).map(producto -> mapper.toProduct(producto));
    }

    /* Guardar un producto */
    @Override
    public Product save(Product product){
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }
/*
    // ***********************************************

    @Override
    public Product updateProduct(Product product){
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }

    //***********************************************
*/

    /* Eliminar un producto */
    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}

/** Estos métodos están orientados a la base de datos directamente!!!
 *      Método para obtener toda la lista de productos que necesito
        *public List<Producto> getAll(){
        *return(List<Producto>)productoCrudRepository.findAll();
        *}
        *
        *     //Método para obtener la lista de productos por idCategoria
        *public List<Producto> getByCategoria(int idCategoria){
        *return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
        *}
        *
        *     // Metodo para obtener los productos escasos
        *public Optional<List<Producto>>getEscasos(int cantidad){
        *return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad,true);
        *}
        *
        *     // Consultar un producto en particular
        *public Optional<Producto> getProducto(int idProducto){
        *return productoCrudRepository.findById(idProducto);
        *}
        *
        *     // Guardar un producto
        *
        *public Producto save(Producto producto){
        *return productoCrudRepository.save(producto);
        *}
        *
        *     // Eliminar un producto
        *public void delete(int idProducto){
        *productoCrudRepository.deleteById(idProducto);
        *}
 */
