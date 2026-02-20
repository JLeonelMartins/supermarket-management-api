package com.martinsjonathan.supermarket.service;

import com.martinsjonathan.supermarket.dto.ProductoDTO;
import com.martinsjonathan.supermarket.dto.ResumenVentasDTO;
import com.martinsjonathan.supermarket.dto.VentaDTO;
import com.martinsjonathan.supermarket.dto.VentaMayorDTO;
import com.martinsjonathan.supermarket.model.Venta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<VentaDTO> obtenerVentas();

    VentaDTO crearVenta(VentaDTO ventaDto);

    VentaDTO actualizarVenta(Long id, VentaDTO ventaDto);

    void eliminarVenta(Long id);

    VentaDTO obtenerVentaPorId(Long id);

    List<ProductoDTO> obtenerProductosPorVenta (Long idVenta);

    ResumenVentasDTO obtenerResumenPorFecha(LocalDate fecha);

    VentaMayorDTO findTopByOrderByTotalDesc();
}
