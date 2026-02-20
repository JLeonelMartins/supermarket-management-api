package com.martinsjonathan.supermarket.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SucursalDTO {
    private Long idSucursal;
    private String nombre;
    private String direccion;
}
