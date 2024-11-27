package com.example.demo.Controller;

import com.example.demo.Model.Jugador;
import com.example.demo.Model.Partida;
import com.example.demo.Services.ChatGPTService;
import com.example.demo.Services.PartidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    private ChatGPTService chatGPTService;

    private PartidaService partidaService;


    @PostMapping("/unirse")
    public ResponseEntity<Partida> unirseAPartida(@RequestBody Jugador jugador) {
        Partida partida = partidaService.unirseAPartida(jugador);
        if (partida != null) {
            return ResponseEntity.ok(partida);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // No hay partida activa
    }

    @PostMapping("/crear")
    public ResponseEntity<Partida> crearPartida(@RequestBody Jugador host) {
        Partida partida = partidaService.crearPartida(host);
        return ResponseEntity.ok(partida);
    }

    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizarPartida() {
        partidaService.finalizarPartida();
        return ResponseEntity.ok("Partida finalizada");
    }

    @GetMapping("/estado")
    public ResponseEntity<Partida> obtenerEstado() {
        Partida partida = partidaService.obtenerPartidaActiva();
        if (partida != null) {
            return ResponseEntity.ok(partida);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
