package com.modulo.certificaciones.repository;


import com.modulo.certificaciones.models.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByUsuarioId(Long usuarioId);

    List<Solicitud> findByEstadoSolicitud(String estadoSolicitud);
}
