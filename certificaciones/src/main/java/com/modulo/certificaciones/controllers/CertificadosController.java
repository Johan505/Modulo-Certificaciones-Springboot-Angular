package com.modulo.certificaciones.controllers;

import com.modulo.certificaciones.services.impl.UserDetailsServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.modulo.certificaciones.models.Solicitud;
import com.modulo.certificaciones.models.Usuario;
import com.modulo.certificaciones.repository.SolicitudRepository;
import com.modulo.certificaciones.repository.UsuarioRepository;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/certificados")
@CrossOrigin("*")
public class CertificadosController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/empleado/{id}")
    public Usuario getUserById (@PathVariable Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }

//    @PostMapping("/pazysalvo/{id}")
//    public ResponseEntity<String> registrarPazySalvo(@PathVariable("id") Long id) {
//        // Obtener el usuario
//        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
//        if (usuarioOptional.isEmpty()) {
//            return ResponseEntity.badRequest().body("El usuario con ID " + id + " no existe");
//        }
//        Usuario usuario = usuarioOptional.get();
//
//        // Crear la solicitud
//        Solicitud solicitud = new Solicitud();
//        solicitud.setUsuario(usuario);
//        solicitud.setFechaHoraSolicitud(LocalDateTime.now());
//        solicitud.setEstado_solicitud(false);
//        solicitud.setEstado_certificado(false);
//        solicitudRepository.save(solicitud);
//
//        return ResponseEntity.ok("Solicitud de paz y salvo registrada exitosamente para el usuario con ID: " + id);
//    }
}