package com.martinsjonathan.supermarket.service;

import com.martinsjonathan.supermarket.dto.ClienteDTO;

import java.util.List;

public interface IClienteService {

    ClienteDTO createCliente(ClienteDTO clienteDTO);

    ClienteDTO getClienteById(Long id);

    List<ClienteDTO> getAllClientes();

    void deleteClienteById(Long id);

    ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO);



}
