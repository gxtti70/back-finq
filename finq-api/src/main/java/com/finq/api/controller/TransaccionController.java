package com.finq.api.controller;

import com.finq.api.entity.Transaccion;
import com.finq.api.repository.CuentaRepository;
import com.finq.api.repository.TransaccionRepository;
import com.finq.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacciones")
@CrossOrigin(origins = "*", allowedHeaders = "*") 
public class TransaccionController {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Traemos el repo de las cuentas
    @Autowired
    private CuentaRepository cuentaRepository;

    @GetMapping
    public ResponseEntity<?> obtenerTodas() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(transaccionRepository.findByUsuarioEmail(email));
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Transaccion transaccion) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        //Buscamos las tarjetas del parcero logueado
        var cuentas = cuentaRepository.findByUsuarioEmail(email);
        
        if (cuentas.isEmpty()) {
            return ResponseEntity.badRequest().body("No tienes tarjetas creadas. Crea una primero.");
        }

        // Le asignamos la transacción a la PRIMERA tarjeta que encuentre
        transaccion.setCuenta(cuentas.get(0));
        
        // Y se la asignamos al usuario también
        transaccion.setUsuario(usuario);
        
        //temporalmente quitamos la categoría obligatoria 
        // para que no estalle, mientras creamos el CRUD de categorías.
        //transaccion.setCategoria(null); 
        
        return ResponseEntity.ok(transaccionRepository.save(transaccion));
    }
    
}
