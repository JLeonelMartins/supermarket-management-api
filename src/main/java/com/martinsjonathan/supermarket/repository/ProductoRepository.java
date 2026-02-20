package com.martinsjonathan.supermarket.repository;

import com.martinsjonathan.supermarket.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    //Buscar producto por nombre
    Optional<Producto> findByNombre(String nombre);

    List<Producto> findByCantidadLessThan(Integer cantidad);


}
