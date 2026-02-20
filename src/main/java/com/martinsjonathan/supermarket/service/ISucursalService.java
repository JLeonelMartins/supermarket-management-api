package com.martinsjonathan.supermarket.service;

import com.martinsjonathan.supermarket.dto.SucursalDTO;
import com.martinsjonathan.supermarket.model.Sucursal;

import java.util.List;

public interface ISucursalService {

    List<SucursalDTO> obtenerSucursales();

    SucursalDTO crearSucursal(SucursalDTO sucursalDto);

    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto);

    void eliminarSucursal(Long id);

    SucursalDTO obtenerSucursalPorId(Long id);




}
