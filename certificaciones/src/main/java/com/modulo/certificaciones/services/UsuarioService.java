package com.modulo.certificaciones.services;

import com.modulo.certificaciones.models.Usuario;
import com.modulo.certificaciones.models.UsuarioRol;

import java.util.Set;

public interface UsuarioService {

    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Long usuarioId);
}