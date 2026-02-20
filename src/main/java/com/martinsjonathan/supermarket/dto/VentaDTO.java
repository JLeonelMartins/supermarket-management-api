package com.martinsjonathan.supermarket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class VentaDTO {

    //Datos de la venta
    private Long idVenta;
    private LocalDate fecha;
    private String estado;

    //datos de la sucursal
    private Long idSucursal;

    //datos del cliente
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idCliente;
    private String nombre_cliente;
    private String apellido;

    //lista de detalles
    private List<DetalleVentaDTO> detalle;

    //total de la venta
    private Double total;

}
