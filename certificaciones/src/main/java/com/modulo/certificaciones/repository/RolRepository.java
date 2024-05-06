package com.modulo.certificaciones.repository;


import com.modulo.certificaciones.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long> {
}