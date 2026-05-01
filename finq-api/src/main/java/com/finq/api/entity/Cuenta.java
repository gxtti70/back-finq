package com.finq.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nombre; // Ej: "Nequi", "Tarjeta Naranja", "Efectivo"

    @Column(nullable = false)
    private String tipo; // Ej: "DEBITO", "CREDITO", "BILLETERA"

    @Column(nullable = false)
    private Double saldo = 0.0;

    @Column(name = "color_hex")
    private String colorHex; // Para que en Angular cada tarjeta tenga su color

    // 🟢 Relación: Esta cuenta tiene un dueño
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore // Evita que al pedir la cuenta, traiga toda la info privada del usuario y haga un bucle infinito
    private Usuario usuario;
}