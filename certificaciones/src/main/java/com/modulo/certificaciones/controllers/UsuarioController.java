package com.modulo.certificaciones.controllers;


import com.modulo.certificaciones.JsonResponse;
import com.modulo.certificaciones.models.Rol;
import com.modulo.certificaciones.models.Solicitud;
import com.modulo.certificaciones.models.Usuario;
import com.modulo.certificaciones.models.UsuarioRol;
import com.modulo.certificaciones.repository.SolicitudRepository;
import com.modulo.certificaciones.repository.UsuarioRepository;
import com.modulo.certificaciones.services.UsuarioService;
import com.modulo.certificaciones.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setRolNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }


    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }


    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal){
        return (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
    }

    @PostMapping("/pazysalvo")
    public ResponseEntity<JsonResponse> registrarPazySalvo(Principal principal) {

        // Obtener el nombre de usuario del Principal
        String username = principal.getName();

        // Buscar el usuario en la base de datos por el nombre de usuario
        Usuario usuario = usuarioRepository.findByUsername(username);

        if (usuario == null) {
            return ResponseEntity.badRequest().body(new JsonResponse("Usuario no encontrado"));
        }

        // Obtener el ID del usuario
        Long userId = usuario.getId();
        List<Solicitud> solicitudesUsuario = solicitudRepository.findByUsuarioId(userId);

        // Verificar si el usuario tiene una solicitud pendiente
        boolean tieneSolicitudPendiente = false;
        for (Solicitud solicitud : solicitudesUsuario) {
            if (solicitud.getEstadoSolicitud().equals("PENDIENTE") || solicitud.getEstadoSolicitud().equals("LISTO")) {
                tieneSolicitudPendiente = true;
                break;
            }
        }

        if (tieneSolicitudPendiente) {
            return ResponseEntity.badRequest().body(new JsonResponse("Usted ya tiene una solicitud pendiente"));
        }

        // Crear la solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setUsuario(usuario); // Asignar el usuario a la solicitud
        solicitud.setFechaHoraSolicitud(LocalDateTime.now());
        solicitud.setEstadoSolicitud("PENDIENTE");
        solicitud.setEstadoCertificado(false);
        solicitudRepository.save(solicitud);

        return ResponseEntity.ok(new JsonResponse("Solicitud registrada"));
    }


    @GetMapping("/solicitudes/{usuarioId}")
    public List<Solicitud> getSolicitudesByUsuarioId(@PathVariable Long usuarioId) {
        return solicitudRepository.findByUsuarioId(usuarioId);
    }

}