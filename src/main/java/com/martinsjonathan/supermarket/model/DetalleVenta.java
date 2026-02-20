package com.martinsjonathan.supermarket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class DetalleVenta {

    //Detalle propio de DetalleVenta
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    //Venta a la que pertenece
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name= "VentaId")
    private Venta venta;

    //Producto
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name= "productoId")
    private Producto producto;
    private Integer cantProd;
    private Double precio;
}
