package com.martinsjonathan.supermarket.service;

import com.martinsjonathan.supermarket.Mapper.Mapper;
import com.martinsjonathan.supermarket.dto.ProductoDTO;
import com.martinsjonathan.supermarket.exception.NotFoundException;
import com.martinsjonathan.supermarket.model.Producto;
import com.martinsjonathan.supermarket.repository.ProductoRepository;
import com.martinsjonathan.supermarket.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> obtenerProductos() {
        return productoRepository.findAll().stream().map(Mapper::toDTO).toList();

    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        Producto producto = Producto.builder()
                .nombre(productoDto.getNombre())
                .categoria(productoDto.getCategoria())
                .precio(productoDto.getPrecio())
                .cantidad(productoDto.getCantidad())
                .build();
        return Mapper.toDTO(productoRepository.save(producto));

    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        //Buscamos si existe el producto
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro el producto"));

        producto.setNombre(productoDto.getNombre());
        producto.setCategoria(productoDto.getCategoria());
        producto.setPrecio(productoDto.getPrecio());
        producto.setCantidad(productoDto.getCantidad());

        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new NotFoundException("Producto no entontrado para eliminar");
        }
        productoRepository.deleteById(id);
    }

    @Override
    public ProductoDTO obtenerProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto == null) throw new NotFoundException("No se encontro el producto");

        ProductoDTO productoDto = Mapper.toDTO(producto);
        return productoDto;
    }

    @Override
    public List<ProductoDTO> findByCantidadLessThan() {
        return productoRepository.findByCantidadLessThan(5)
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }




}
