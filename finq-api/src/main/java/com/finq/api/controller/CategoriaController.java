package com.finq.api.controller;

import com.finq.api.entity.Categoria;
import com.finq.api.repository.CategoriaRepository;
import com.finq.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoriaes")
@CrossOrigin(origins = "*", allowedHeaders = "*") // 🚀 Eliminamos el bloqueo de CORS para este controlador
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerMisDatos() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(categoriaRepository.findByUsuarioEmail(email));
    }

    @PostMapping
    public ResponseEntity<?> guardarDato(@RequestBody Categoria objeto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        objeto.setUsuario(usuario);
        return ResponseEntity.ok(categoriaRepository.save(objeto));
    }
}
