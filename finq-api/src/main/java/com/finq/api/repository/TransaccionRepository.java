package com.finq.api.repository;

import com.finq.api.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, UUID> {
}
