package org.aguzman.springcloud.msvc.cursos.models.entity;

import org.aguzman.springcloud.msvc.cursos.models.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    private List<CursosUsuarios> cursosUsuarios;

    @Transient
    private List<Usuario> usuarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CursosUsuarios> getCursosUsuarios() {
        return cursosUsuarios;
    }

    public void setCursosUsuarios(List<CursosUsuarios> cursosUsuarios) {
        this.cursosUsuarios = cursosUsuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
