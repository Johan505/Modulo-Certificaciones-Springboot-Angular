package com.modulo.certificaciones.models;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "solicitudes", schema = "certificados")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Solicitud{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private LocalDateTime fechaHoraSolicitud;

    private String estadoSolicitud;

    private boolean estadoCertificado;

    public Solicitud() {
    }


    public Solicitud(Long id, Usuario usuario, LocalDateTime fechaHoraSolicitud, String estadoSolicitud, boolean estadoCertificado) {
        this.id = id;
        this.usuario = usuario;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
        this.estadoSolicitud = estadoSolicitud;
        this.estadoCertificado = estadoCertificado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(LocalDateTime fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public String isEstadoSolicitud() {
        return estadoSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public boolean isEstadoCertificado() {
        return estadoCertificado;
    }

    public void setEstadoCertificado(boolean estadoCertificado) {
        this.estadoCertificado = estadoCertificado;
    }
}

