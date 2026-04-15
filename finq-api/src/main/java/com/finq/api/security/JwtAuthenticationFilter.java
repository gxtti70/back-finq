package com.finq.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // CHISMOSOS ACTIVADOS
        System.out.println("--------------------------------------------------");
        System.out.println("🔍 Petición a: " + request.getRequestURI());
        System.out.println("🔑 Cabecera Auth: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("❌ Resultado: Sin token válido, ignorando filtro.");
            System.out.println("--------------------------------------------------");
            filterChain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);

        try {
            if (jwtUtil.validarToken(jwt)) {
                System.out.println("✅ Token VÁLIDO.");
                String email = jwtUtil.extraerEmail(jwt);
                System.out.println("👤 Email extraído: " + email);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            email, null, new ArrayList<>());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println("🚪 ¡Puerta abierta en SecurityContext para " + email + "!");
                }
            } else {
                System.out.println("🛑 RESULTADO: El token expiró o la firma es inválida.");
            }
        } catch (Exception e) {
            System.out.println("💥 ERROR CRÍTICO validando el token: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("--------------------------------------------------");
        filterChain.doFilter(request, response);
    }
}
