package com.martinsjonathan.supermarket.service;

import com.martinsjonathan.supermarket.Mapper.Mapper;
import com.martinsjonathan.supermarket.dto.SucursalDTO;
import com.martinsjonathan.supermarket.exception.NotFoundException;
import com.martinsjonathan.supermarket.model.Sucursal;
import com.martinsjonathan.supermarket.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService{

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<SucursalDTO> obtenerSucursales() {
        return sucursalRepository.findAll()
                .stream()
                .map(Mapper::toDTO)
                .toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDto) {
        Sucursal sucursal = Sucursal.builder()
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto) {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontro la sucursal"));

        sucursal.setNombre(sucursalDto.getNombre());
        sucursal.setDireccion(sucursalDto.getDireccion());

        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public void eliminarSucursal(Long id) {
        if (!sucursalRepository.existsById(id)) {
            throw new NotFoundException("Sucursal no encontrada para eliminar");
        }
        sucursalRepository.deleteById(id);
    }

    @Override
    public SucursalDTO obtenerSucursalPorId(Long id) {
        Sucursal sucursal = sucursalRepository.findById(id).orElse(null);
        if (sucursal == null) throw new NotFoundException("No se encontro la sucursal");

        SucursalDTO sucursalDto = Mapper.toDTO(sucursal);
        return sucursalDto;
    }
}
