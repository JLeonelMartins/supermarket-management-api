package com.martinsjonathan.supermarket.service;

import com.martinsjonathan.supermarket.Mapper.Mapper;
import com.martinsjonathan.supermarket.dto.ClienteDTO;
import com.martinsjonathan.supermarket.exception.NotFoundException;
import com.martinsjonathan.supermarket.model.Cliente;
import com.martinsjonathan.supermarket.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        Cliente cliente = Cliente.builder()
                .nombre_cliente(clienteDTO.getNombre())
                .apellido(clienteDTO.getApellido())
                .dni(clienteDTO.getDni())
                .build();

        return Mapper.toClienteDTO(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO getClienteById(Long id) {
        return clienteRepository.findById(id)
                .map(Mapper::toClienteDTO)
                .orElse(null);
    }

    @Override
    public List<ClienteDTO> getAllClientes() {
        return clienteRepository.findAll()
                .stream().map(Mapper::toClienteDTO).toList();
    }

    @Override
    public void deleteClienteById(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new NotFoundException("Cliente no encontrado");
        }
        clienteRepository.deleteById(id);

    }

    @Override
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

        cliente.setNombre_cliente(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setDni(clienteDTO.getDni());

        return Mapper.toClienteDTO(clienteRepository.save(cliente));
    }
}
