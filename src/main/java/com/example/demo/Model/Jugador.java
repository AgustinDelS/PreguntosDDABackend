package com.example.demo.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String contrasena;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pregunta> aciertos;

    public Jugador(String nombre, String contrasena) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.aciertos = new ArrayList<>();
    }

    public Jugador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarAcierto(Pregunta pregunta) {
        this.aciertos.add(pregunta);
    }

    public void setAciertos(List<Pregunta> aciertos) {
        this.aciertos = aciertos;
    }

    public List<Pregunta> getAciertos() {
        return this.aciertos;
    }
}
