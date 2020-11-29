package com.developer.marketspring.persistence.crud;


import com.developer.marketspring.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    /* Recuperar la lista de productos que pertenezcan a una categoria en especifico */

    /* @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true) */
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    /* Recuperar la lista de productos escasos */

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
