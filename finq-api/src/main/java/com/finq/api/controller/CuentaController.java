package com.finq.api.controller;

import com.finq.api.entity.Cuenta;
import com.finq.api.repository.CuentaRepository;
import com.finq.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<?> obtenerMisCuentas() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(cuentaRepository.findByUsuarioEmail(email));
    }

    // Método para crear una tarjeta nueva
    @PostMapping
    public ResponseEntity<?> crearCuenta(@RequestBody Cuenta cuenta) {
        // Buscamos quién es el dueño según el Token
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Le asignamos el dueño a la cuenta y guardamos
        cuenta.setUsuario(usuario);
        return ResponseEntity.ok(cuentaRepository.save(cuenta));
    }
}
