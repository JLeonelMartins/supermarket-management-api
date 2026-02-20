package com.martinsjonathan.supermarket.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductoDTO {
    private Long idProducto;
    private String nombre;
    private String categoria;
    private Double precio;
    private int cantidad;
}
