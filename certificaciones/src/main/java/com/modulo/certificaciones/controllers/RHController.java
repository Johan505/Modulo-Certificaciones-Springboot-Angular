package com.modulo.certificaciones.controllers;

import com.modulo.certificaciones.models.Equipo;
import com.modulo.certificaciones.models.Solicitud;
import com.modulo.certificaciones.models.Usuario;
import com.modulo.certificaciones.repository.EquipoRepository;
import com.modulo.certificaciones.repository.SolicitudRepository;
import com.modulo.certificaciones.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/solicitudes")
public class RHController {

    @Autowired
    SolicitudRepository solicitudRepository;


    @Autowired
    EquipoRepository equipoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public List<Solicitud> obtenerSolicitudesPendientes() {
        return solicitudRepository.findByEstadoSolicitud("PENDIENTE");
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> actualizarSolicitud(@PathVariable Long id, @RequestBody String solicitudActualizada) {
        return solicitudRepository.findById(id).map(solicitud -> {
            solicitud.setEstadoSolicitud(solicitudActualizada);
            solicitudRepository.save(solicitud);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/solicitud/{id}")
    public List<Solicitud> getSolicitudByuserId(@PathVariable Long usuarioId) {
        return solicitudRepository.findByUsuarioId(usuarioId);
    }



    @GetMapping("/entrega/{id}")
    public List<Equipo> getEquiposByUsuarioId(@PathVariable Long id) {
        return equipoRepository.findByUsuarioId(id);
    }


}
