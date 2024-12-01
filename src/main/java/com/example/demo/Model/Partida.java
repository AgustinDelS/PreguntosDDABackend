package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    public static Jugador host;
    public static List<Jugador> jugadores = new ArrayList<>();
    public static String categoriaActual;
    public static String preguntaActual;
    public static String respuestaCorrecta;
    public static boolean activa;


    public Partida(Jugador hostt) {
        host = hostt;
        jugadores.add(host);
        activa = true;
    }

    public static boolean isActiva() {
        return activa;
    }

    public static void setActiva(boolean activa) {
        Partida.activa = activa;
    }

    public static String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }


    public static synchronized void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public static synchronized void setPregunta(String pregunta, String respuestaCorrecta) {
        preguntaActual = pregunta;
        respuestaCorrecta = respuestaCorrecta;
    }

    public static synchronized List<Jugador> getJugadores() {
        return new ArrayList<>(jugadores); // Devuelve una copia para evitar modificaciones externas
    }


    public static void setJugadores(List<Jugador> jugadoress) {
        jugadores = jugadoress;
    }

    public static void setCategoriaActual(String categoriaActuall) {
        categoriaActual = categoriaActuall;
    }

    public static void setPreguntaActual(String preguntaActuall) {
        preguntaActual = preguntaActuall;
    }

    public static void setHost(Jugador hostt) {
        host = hostt;
    }


    public static String getCategoriaActual() {
        return categoriaActual;
    }

    public static String getPreguntaActual() {
        return preguntaActual;
    }

    public static Jugador getHost() {
        return host;
    }

    public static synchronized void reset() {
        host = null; // Limpia el host
        jugadores.clear(); // Vacía la lista de jugadores
        categoriaActual = null; // Limpia la categoría actual
        preguntaActual = null; // Limpia la pregunta actual
        respuestaCorrecta = null; // Limpia la respuesta correcta
        activa = false;
    }

}
