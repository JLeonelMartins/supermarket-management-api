package com.martinsjonathan.supermarket.service;

import com.martinsjonathan.supermarket.Mapper.Mapper;
import com.martinsjonathan.supermarket.dto.*;
import com.martinsjonathan.supermarket.exception.NotFoundException;
import com.martinsjonathan.supermarket.model.*;
import com.martinsjonathan.supermarket.repository.ClienteRepository;
import com.martinsjonathan.supermarket.repository.ProductoRepository;
import com.martinsjonathan.supermarket.repository.SucursalRepository;
import com.martinsjonathan.supermarket.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<VentaDTO> obtenerVentas() {

        List<Venta> ventas = ventaRepository.findAll();
        List<VentaDTO> ventasDTO = new ArrayList<VentaDTO>();
        VentaDTO dto;

        for (Venta v : ventas) {
            dto = Mapper.toDTO(v);
            ventasDTO.add(dto);
        }
        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {

        //Validaciones
        if (ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        //Buscar la sucursal
        Sucursal suc = sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);
        if (suc == null) {
            throw new NotFoundException("Sucursal no encontrada");
        }
        if (ventaDto.getIdCliente() == null)
            throw new RuntimeException("Debe indicar el cliente");

        // Buscar cliente
        Cliente cliente = clienteRepository
                .findById(ventaDto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));


        //Crear la venta
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setSucursal(suc);
        vent.setUnCliente(cliente);
        vent.setTotal(ventaDto.getTotal());

        // La lista de detalles
        // --> Acá están los productos
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetalleVentaDTO detDTO : ventaDto.getDetalle()) {
            // Buscar producto por id (tu detDTO usa id como id de producto)
            Producto p = productoRepository.findByNombre(detDTO.getNombreProd()).orElse(null);
            if (p == null)
            {throw new RuntimeException("Producto no encontrado: " + detDTO.getNombreProd());}

            //Crear detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProducto(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setVenta(vent);

            detalles.add(detalleVent);
            totalCalculado = totalCalculado+(detDTO.getPrecio()*detDTO.getCantProd());

        }
        //Seteamos la lista de detalle Venta
        vent.setDetalle(detalles);

        //guardamos en la BD
        vent = ventaRepository.save(vent);

        //Mapeo de salida
        VentaDTO ventaSalida = Mapper.toDTO(vent);
        return ventaSalida;

    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        //Buscamos si venta existe para actualizar
        Venta venta = ventaRepository.findById(id).orElse(null);
        if(venta == null) throw new RuntimeException("Venta de id " + id + " no encontrada");

        if(ventaDto.getFecha() != null){
            venta.setFecha(ventaDto.getFecha());
        }
        if(ventaDto.getEstado() != null){
            venta.setEstado(ventaDto.getEstado());
        }
        if (ventaDto.getTotal() != null){
            venta.setTotal(ventaDto.getTotal());
        }
        if(ventaDto.getIdSucursal() != null){
            Sucursal suc = sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada");
            venta.setSucursal(suc);
        }
        if (ventaDto.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(ventaDto.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            venta.setUnCliente(cliente);
        }

        ventaRepository.save(venta);

        VentaDTO ventaSalida = Mapper.toDTO(venta);
        return ventaSalida;


    }

    @Override
    public void eliminarVenta(Long id) {

        Venta venta = ventaRepository.findById(id).orElse(null);
        if(venta == null) throw new RuntimeException("Venta de id " + id + " no encontrada");
        ventaRepository.delete(venta);

    }

    @Override
    public VentaDTO obtenerVentaPorId(Long id) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if(venta == null) throw new RuntimeException("Venta de id " + id + " no encontrada");

        VentaDTO ventaSalida = Mapper.toDTO(venta);
        return ventaSalida;
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorVenta(Long idVenta) {
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));

        return venta.getDetalle()
                .stream()
                .map(det -> Mapper.toDTO(det.getProducto()))
                .toList();
    }

    @Override
    public ResumenVentasDTO obtenerResumenPorFecha(LocalDate fecha) {
        return ventaRepository.obtenerResumenPorFecha(fecha);
    }

    @Override
    public VentaMayorDTO findTopByOrderByTotalDesc() {
        Venta venta = ventaRepository
                .findTopByOrderByTotalDesc()
                .orElseThrow(() -> new NotFoundException("No hay ventas"));

        Integer cantidadProductos = venta.getDetalle()
                .stream()
                .mapToInt(DetalleVenta::getCantProd)
                .sum();

        return VentaMayorDTO.builder()
                .codigoVenta(venta.getIdVenta())
                .total(venta.getTotal())
                .cantidadProductos(cantidadProductos)
                .nombreSucursal(venta.getSucursal().getNombre())
                .direccion(venta.getSucursal().getDireccion())
                .nombreCliente(venta.getUnCliente().getNombre_cliente())
                .apellidoCliente(venta.getUnCliente().getApellido())
                .build();
    }


}
