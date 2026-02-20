package com.martinsjonathan.supermarket.controller;

import com.martinsjonathan.supermarket.dto.ProductoDTO;
import com.martinsjonathan.supermarket.dto.ResumenVentasDTO;
import com.martinsjonathan.supermarket.dto.VentaDTO;
import com.martinsjonathan.supermarket.dto.VentaMayorDTO;
import com.martinsjonathan.supermarket.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> traerVentas() {
        return ResponseEntity.ok(ventaService.obtenerVentas());
    }

    //Crea una venta usando directament VentaDTO en la request (opcion simple, sin request separado)
    //Se espera que el DTO traiga la informacion

    @PostMapping
    public ResponseEntity<VentaDTO> crearVenta(@RequestBody VentaDTO ventaDTO) {
        VentaDTO creada = ventaService.crearVenta(ventaDTO);

        return ResponseEntity.created(URI.create("/api/ventas" + creada.getIdVenta())).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok(ventaService.actualizarVenta(id, ventaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VentaDTO> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerVentaPorId(@PathVariable Long id) {
        VentaDTO venta = ventaService.obtenerVentaPorId(id);
        return ResponseEntity.ok(venta);
    }

    //Obtener la lista de productos de una determinada venta
    @GetMapping("/producto/{idVenta}")
    public ResponseEntity<List<ProductoDTO>> obtenerProductosPorVenta(@PathVariable Long idVenta) {
        List<ProductoDTO> lista = ventaService.obtenerProductosPorVenta(idVenta);
        return ResponseEntity.ok(lista);
    }

    //Obtener la sumatoria del monto y también cantidad total de ventas de un determinado día
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ResumenVentasDTO> obtenerResumenPorFecha(@PathVariable LocalDate fecha) {
        ResumenVentasDTO resumenVentasDTO = ventaService.obtenerResumenPorFecha(fecha);
        return ResponseEntity.ok(resumenVentasDTO);
    }
    @GetMapping("/mayor_venta")
    public ResponseEntity<VentaMayorDTO> obtenerVentaMayor() {
        return ResponseEntity.ok(ventaService.findTopByOrderByTotalDesc());
    }



}
