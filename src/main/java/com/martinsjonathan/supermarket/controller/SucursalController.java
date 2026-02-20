package com.martinsjonathan.supermarket.controller;

import com.martinsjonathan.supermarket.dto.SucursalDTO;
import com.martinsjonathan.supermarket.service.ISucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private ISucursalService sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalDTO>> traerSucursales() {
        return ResponseEntity.ok(sucursalService.obtenerSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDTO> crearSucursal(@RequestBody SucursalDTO sucursalDTO) {
        SucursalDTO creada = sucursalService.crearSucursal(sucursalDTO);
        return ResponseEntity.created((URI.create("/api/sucursales" + creada.getIdSucursal()))).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDTO> actualizarSucursal(@PathVariable Long id, @RequestBody SucursalDTO sucursalDTO) {
        return ResponseEntity.ok(sucursalService.actualizarSucursal(id, sucursalDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SucursalDTO> borrarSucursal(@PathVariable Long id) {
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDTO> obtenerSucursalPorId(@PathVariable Long id) {
        SucursalDTO sucursalDTO = sucursalService.obtenerSucursalPorId(id);
        return ResponseEntity.ok(sucursalDTO);
    }


}
