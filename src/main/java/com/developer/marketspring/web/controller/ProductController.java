package com.developer.marketspring.web.controller;

import com.developer.marketspring.domain.Eschema.Product;
import com.developer.marketspring.domain.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Esta clase es el controlador de la API rest
@RequestMapping("/products") // Lleva como parametro el path al que se hacen las peticiones
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    @ApiOperation(value = "Ver todos los productos", authorizations = {
            @Authorization(value="JWT") })
    @ApiResponse(code = 200, message = "Ok")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    @ApiOperation("Buscar una categoria por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Categoria no encontrada")
    })
    public ResponseEntity<List<Product>> getByCategory(@ApiParam(value = "Id del producto requerido",
            required = true, example = "1") @PathVariable("id") int categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
/*
    public Optional<List<Product>> getScarseProducts(int quantity){
        return productService.getScarseProducts(quantity);
    }
*/
    @GetMapping("/{id}")
    @ApiOperation("Buscar un producto por id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Producto no encontrado")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "Id del producto requerido",
    required = true, example = "7") @PathVariable("id") int productId){
        return productService.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable("id") int id) {
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
        if (productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}

/**
 * @GetMapping("/all")
 *     public List<Product> getAll(){
 *         return productService.getAll();
 *     }
 *
 *     @GetMapping("/category/{id}")
 *     public Optional<List<Product>> getByCategory(@PathVariable("id") int categoryId){
 *         return productService.getByCategory(categoryId);
 *     }
 * //
 * //  public Optional<List<Product>> getScarseProducts(int quantity){
 * //      return productService.getScarseProducts(quantity);
 * //    }
 *
 *@GetMapping("/{id}")
 *public Optional<Product> getProduct(@PathVariable("id") int productId){
        *return productService.getProduct(productId);
        *}
        *
        *@PostMapping("/save")
 *public Product save(@RequestBody Product product){
        *return productService.save(product);
        *}
        *
        *@PutMapping("/update/{id}")
 *public Product updateProduct(@RequestBody Product product,@PathVariable("id") int id){
        *return productService.updateProduct(product,id);
        *}
        *
        *@DeleteMapping("/delete/{id}")
 *public boolean delete(@PathVariable("id") int productId){
        *return productService.delete(productId);
        *}
 */
