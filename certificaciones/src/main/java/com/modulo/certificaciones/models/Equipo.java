package com.modulo.certificaciones.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "equipos", schema = "certificados")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private boolean devuelto;
    @Temporal(TemporalType.DATE)
    private Date fecha_devolucion;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
