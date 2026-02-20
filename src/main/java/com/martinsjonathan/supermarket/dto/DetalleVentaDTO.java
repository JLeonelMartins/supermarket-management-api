package com.martinsjonathan.supermarket.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DetalleVentaDTO {

    private Long idDetalle;
    private String nombreProd;
    private Integer cantProd;
    private Double precio;
    private Double subtotal;
}
