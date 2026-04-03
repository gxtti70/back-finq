package com.finq.api.controller;

import com.finq.api.entity.Transaccion;
import com.finq.api.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionRepository transaccionRepository;

    // Obtener todos los gastos/ingresos
    @GetMapping
    public List<Transaccion> obtenerTodas() {
        return transaccionRepository.findAll();
    }

    // Registrar un nuevo gasto/ingreso
    @PostMapping
    public Transaccion guardar(@RequestBody Transaccion transaccion) {
        return transaccionRepository.save(transaccion);
    }
}
