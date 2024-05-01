package com.modulo.certificaciones.repository;

import com.modulo.certificaciones.models.Equipo;
import com.modulo.certificaciones.models.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByUsuarioId(Long id);
}
