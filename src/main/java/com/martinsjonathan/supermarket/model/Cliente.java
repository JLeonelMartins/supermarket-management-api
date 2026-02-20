package com.martinsjonathan.supermarket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;
    private String nombre_cliente;
    private String apellido;
    private String dni;

    @OneToMany(mappedBy = "unCliente")
    private List<Venta> ventas;

}

