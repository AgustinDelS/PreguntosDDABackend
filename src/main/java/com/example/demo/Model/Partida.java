package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private String id; // Código único para la partida
    private List<Jugador> jugadores = new ArrayList<>();
    private String categoriaActual; // Categoría seleccionada
    private String preguntaActual; // Pregunta enviada
    private Jugador host; // Host de la partida

    public Partida(String id, Jugador host) {
        this.id = id;
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void setCategoriaActual(String categoriaActual) {
        this.categoriaActual = categoriaActual;
    }

    public void setPreguntaActual(String preguntaActual) {
        this.preguntaActual = preguntaActual;
    }

    public void setHost(Jugador host) {
        this.host = host;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public String getCategoriaActual() {
        return categoriaActual;
    }

    public String getPreguntaActual() {
        return preguntaActual;
    }

    public Jugador getHost() {
        return host;
    }
}
