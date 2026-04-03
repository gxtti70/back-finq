package com.finq.api.repository;

import com.finq.api.entity.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, UUID> {
}
