package com.martinsjonathan.supermarket.service;

import com.martinsjonathan.supermarket.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> obtenerProductos();

    ProductoDTO crearProducto(ProductoDTO productoDto);

    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto);

    void eliminarProducto(Long id);

    ProductoDTO obtenerProductoPorId(Long id);

    List<ProductoDTO> findByCantidadLessThan();



}
