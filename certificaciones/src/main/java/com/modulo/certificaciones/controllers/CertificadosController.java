package com.modulo.certificaciones.controllers;


import com.modulo.certificaciones.models.Solicitud;
import com.modulo.certificaciones.models.Usuario;
import com.modulo.certificaciones.repository.SolicitudRepository;
import com.modulo.certificaciones.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/certificados")
public class CertificadosController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @GetMapping("/empleado/{id}")
    public Usuario getUserById (@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }


    @PostMapping("/pazysalvo/{id}")
    public ResponseEntity<String> registrarPazySalvo(@PathVariable("id") Long id) {
        // Obtener el usuario
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("El usuario con ID " + id + " no existe");
        }
        Usuario usuario = usuarioOptional.get();

        // Crear la solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setUsuario(usuario);
        solicitud.setFechaHoraSolicitud(LocalDateTime.now());
        solicitud.setEstado(false);
        solicitudRepository.save(solicitud);

        return ResponseEntity.ok("Solicitud de paz y salvo registrada exitosamente para el usuario con ID: " + id);
    }
}