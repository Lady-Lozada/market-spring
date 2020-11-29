package com.developer.marketspring.persistence.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "compras_productos")
public class ComprasProducto {

    @EmbeddedId //Se usa para indicarle a java que tiene una PK compuesta y esta dada por otra clase
    private ComprasProductoPK id;

    private Integer cantidad;

    private BigDecimal total;

    private Boolean estado;


    /* Relacion entre la tabla compras y los compras_productos ⬇ */

    @ManyToOne
    @JoinColumn(name = "id_compra", insertable = false, updatable = false)
    private Compra compra;


    /* Relación entre la tabla productos y las compras_productos ⬇ */

    @ManyToOne
    @JoinColumn(name = "id_producto", insertable = false, updatable = false)
    private Producto producto;


    /* Getters and Setters ⬇ */

    public ComprasProductoPK getId() {
        return id;
    }

    public void setId(ComprasProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
