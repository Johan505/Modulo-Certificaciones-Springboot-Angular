package com.modulo.certificaciones.controllers;

import com.modulo.certificaciones.models.Equipo;
import com.modulo.certificaciones.models.Solicitud;
import com.modulo.certificaciones.models.Usuario;
import com.modulo.certificaciones.repository.EquipoRepository;
import com.modulo.certificaciones.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
public class RHController {

    @Autowired
    SolicitudRepository solicitudRepository;
    @Autowired
    EquipoRepository equipoRepository;

    @GetMapping("/")
    public List<Solicitud> getAll() {
        return solicitudRepository.findAll();
    }

    @GetMapping("/{id}")
    public Solicitud getSolicitudById(@PathVariable Long id) {
        return solicitudRepository.findById(id).orElse(null);
    }


    @GetMapping("/entrega/{id}")
    public List<Equipo> getEquiposByUsuarioId(@PathVariable Long id) {
        return equipoRepository.findByUsuarioId(id);
    }

    
}
