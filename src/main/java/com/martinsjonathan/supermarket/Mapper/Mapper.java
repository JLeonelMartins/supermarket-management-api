package com.martinsjonathan.supermarket.Mapper;

import com.martinsjonathan.supermarket.dto.*;
import com.martinsjonathan.supermarket.model.Cliente;
import com.martinsjonathan.supermarket.model.Producto;
import com.martinsjonathan.supermarket.model.Sucursal;
import com.martinsjonathan.supermarket.model.Venta;


import java.util.stream.Collectors;

public class Mapper {

    //Mapeo de Producto a ProductoDTO
    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;

        return ProductoDTO.builder()
                .idProducto(p.getIdProducto())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .cantidad(p.getCantidad())
                .build();
    }

    //Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        var detalle = venta.getDetalle().stream().map(det ->
                DetalleVentaDTO.builder()
                        .idDetalle(det.getProducto().getIdProducto())
                        .nombreProd(det.getProducto().getNombre())
                        .cantProd(det.getCantProd())
                        .precio(det.getPrecio())
                        .subtotal(det.getPrecio() * det.getCantProd())
                        .build()
        ).collect(Collectors.toList());

        var total = detalle.stream()
                .map(DetalleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        Cliente cliente = venta.getUnCliente();

        return VentaDTO.builder()
                .idVenta(venta.getIdVenta())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getIdSucursal())
                .estado(venta.getEstado())
                .nombre_cliente(cliente != null ? cliente.getNombre_cliente() : null)
                .apellido(cliente != null ? cliente.getApellido() : null)
                .detalle(detalle)
                .total(total)
                .build();
    }


    //Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal s) {
        if (s == null) return null;
        return SucursalDTO.builder()
                .idSucursal(s.getIdSucursal())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }

    public static ClienteDTO toClienteDTO(Cliente cliente) {
        if(cliente == null) return null;

        return ClienteDTO.builder()
                .id_cliente(cliente.getId_cliente())
                .nombre(cliente.getNombre_cliente())
                .apellido(cliente.getApellido())
                .dni(cliente.getDni())
                .build();
    }




}
