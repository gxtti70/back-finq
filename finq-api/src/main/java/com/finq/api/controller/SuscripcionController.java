package com.finq.api.controller;

import com.finq.api.entity.Suscripcion;
import com.finq.api.repository.SuscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    // Obtener todas las suscripciones
    @GetMapping
    public List<Suscripcion> obtenerTodas() {
        return suscripcionRepository.findAll();
    }

    // Registrar una nueva suscripción
    @PostMapping
    public Suscripcion guardar(@RequestBody Suscripcion suscripcion) {
        return suscripcionRepository.save(suscripcion);
    }
}
