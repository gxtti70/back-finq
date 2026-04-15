package com.finq.api.service;

import com.finq.api.dto.LoginRequest;
import com.finq.api.dto.RegistroRequest;
import com.finq.api.entity.Usuario;
import com.finq.api.repository.UsuarioRepository;
import com.finq.api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String registrar(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        usuarioRepository.save(nuevoUsuario);
        return "Usuario registrado exitosamente";
    }

    public String login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email o contraseña incorrectos"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new RuntimeException("Email o contraseña incorrectos");
        }

        return jwtUtil.generarToken(usuario.getEmail());
    }
}
