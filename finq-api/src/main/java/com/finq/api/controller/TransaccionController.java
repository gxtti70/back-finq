package com.finq.api.controller;

import com.finq.api.entity.Transaccion;
import com.finq.api.repository.TransaccionRepository;
import com.finq.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
@CrossOrigin(origins = "*")
public class TransaccionController {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Transaccion>> obtenerMisDatos() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(transaccionRepository.findByUsuarioEmail(email));
    }

    @PostMapping
    public ResponseEntity<?> guardarDato(@RequestBody Transaccion objeto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        objeto.setUsuario(usuario);
        return ResponseEntity.ok(transaccionRepository.save(objeto));
    }
}
