package com.example.demo.Services;

import com.example.demo.Model.Jugador;
import com.example.demo.Model.Partida;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PartidaService {
    private Partida partidaActiva; // Una sola partida activa

    public Partida crearPartida(Jugador host) {
        if (partidaActiva == null) { // Solo crea una partida si no existe una activa
            partidaActiva = new Partida(UUID.randomUUID().toString(), host);
        }
        return partidaActiva;
    }
    public Partida unirseAPartida(Jugador jugador) {
        if (partidaActiva != null) {
            partidaActiva.getJugadores().add(jugador); // Añadir jugador
            return partidaActiva;
        }
        return null; // No hay partida activa
    }
    public Partida obtenerPartidaActiva() {
        return partidaActiva;
    }
    public void finalizarPartida() {
        partidaActiva = null; // Finaliza la partida actual
    }
}
