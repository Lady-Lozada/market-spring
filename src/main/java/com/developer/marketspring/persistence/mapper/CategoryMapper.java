package com.developer.marketspring.persistence.mapper;

import com.developer.marketspring.domain.Category;
import com.developer.marketspring.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    /* Diseñamos los mappers (conversores) */

    // Convertimos una categoria en Category
    @Mappings({
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    // Convertimos de Category a categoria
    @InheritInverseConfiguration // Le indica a mappstruct que la conversion es la inversa a la anterior
    @Mapping(target = "productos", ignore = true)
    Categoria toCategoria(Category category);
}
