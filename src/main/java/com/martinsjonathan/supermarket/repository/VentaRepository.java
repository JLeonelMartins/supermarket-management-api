package com.martinsjonathan.supermarket.repository;

import com.martinsjonathan.supermarket.dto.ProductoDTO;
import com.martinsjonathan.supermarket.dto.ResumenVentasDTO;
import com.martinsjonathan.supermarket.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query("""
       SELECT new com.martinsjonathan.supermarket.dto.ResumenVentasDTO(
           COUNT(v),
           SUM(v.total)
       )
       FROM Venta v
       WHERE v.fecha = :fecha
       """)
    ResumenVentasDTO obtenerResumenPorFecha(@Param("fecha") LocalDate fecha);

    Optional<Venta> findTopByOrderByTotalDesc();





}
