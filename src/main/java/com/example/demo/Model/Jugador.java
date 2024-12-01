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

    @Column(nullable = false)
    private int cantidadAciertos = 0;

    @Column(nullable = false)
    private int partidasJugadas = 0;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int puntajeMaximo = 0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pregunta> aciertos = new ArrayList<>();

    // Constructor vacío requerido por JPA
    public Jugador() {
        puntajeMaximo = 0;
    }

    // Constructor con todos los parámetros
    public Jugador(Long id, String nombre, String contrasena, int cantidadAciertos, List<Pregunta> aciertos) {
        this.id = id;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.cantidadAciertos = cantidadAciertos;
        this.aciertos = aciertos;
        this.puntajeMaximo = 0;
        this.partidasJugadas = 0;
    }

    // Constructor básico (sin aciertos ni cantidad de aciertos)
    public Jugador(String nombre, String contrasena,int puntajeMaximo) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.cantidadAciertos = 0;
        this.puntajeMaximo = puntajeMaximo;
        this.partidasJugadas = 0;
    }

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(int putajeMaximo) {
        this.puntajeMaximo = putajeMaximo;
    }

    public void setPartidasJugadas(int partidasJugadas) {
        this.partidasJugadas = partidasJugadas;
    }

    // Getters y setters
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getCantidadAciertos() {
        return cantidadAciertos;
    }

    public void setCantidadAciertos(int cantidadAciertos) {
        this.cantidadAciertos = cantidadAciertos;
    }

    public List<Pregunta> getAciertos() {
        return aciertos;
    }

    public void setAciertos(List<Pregunta> aciertos) {
        this.aciertos = aciertos;
    }

    public void agregarAcierto(Pregunta pregunta) {
        this.aciertos.add(pregunta);
        this.cantidadAciertos++; // Incrementa el contador de aciertos
    }
}
