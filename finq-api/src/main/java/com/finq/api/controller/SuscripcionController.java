package com.finq.api.controller;

import com.finq.api.entity.Suscripcion;
import com.finq.api.repository.SuscripcionRepository;
import com.finq.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suscripciones")
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class SuscripcionController {

    @Autowired
    private SuscripcionRepository suscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Suscripcion>> obtenerMisDatos() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(suscripcionRepository.findByUsuarioEmail(email));
    }

    @PostMapping
    public ResponseEntity<?> guardarDato(@RequestBody Suscripcion objeto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        objeto.setUsuario(usuario);
        return ResponseEntity.ok(suscripcionRepository.save(objeto));
    }
}
