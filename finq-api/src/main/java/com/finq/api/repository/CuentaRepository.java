package com.finq.api.repository;

import com.finq.api.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, UUID> {
    
    // 🟢 Magia de Spring: Busca todas las tarjetas que le pertenecen a un correo
    List<Cuenta> findByUsuarioEmail(String email);
}