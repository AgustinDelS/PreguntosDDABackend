package com.example.demo.Controller;

import com.example.demo.Model.Jugador;
import com.example.demo.Model.Partida;
import com.example.demo.Services.ChatGPTService;
import com.example.demo.Services.PartidaService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/partida")
public class GameController {
    TriviaController triviaController;
    private final SimpMessagingTemplate messagingTemplate;


    public GameController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearPartida(@RequestBody Jugador host) {
        if (!Partida.isActiva()) { // Verifica si ya hay una partida activa
            Partida.setActiva(true);
            Partida.setHost(host);
            Partida.agregarJugador(host);
             // Crea una nueva partida con el host
            System.out.println("La partida se creo en el backesn estoy");
            return ResponseEntity.ok("Partida creada con éxito.");
        }
        System.out.println("La partida NO se creo en el backesn estoy");
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una partida activa.");
    }



    @MessageMapping("/unirse")
    public void unirseAPartida(Jugador jugador) {
        try {
            if (Partida.isActiva()) {
                Partida.agregarJugador(jugador);
                System.out.println("Jugador unido: " + jugador.getNombre());

                if (Partida.getJugadores().size() >= 2) {
                    messagingTemplate.convertAndSend(
                            "/topic/hostPuedeGirarRuleta",
                            "El host ya puede girar la ruleta."
                    );
                }
            } else {
                System.out.println("LA PARTIDA NO APARECE ACTIVA ESTOY EN EL BACKEND");
            }
        } catch (Exception e) {
            System.out.println("Error en unirseAPartida: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @SendTo("/topic/hostPuedeGirarRuleta")
    public String puedeGirarRuleta() {
        return "Afirmativo";
    }

    @Autowired
    private ChatGPTService chatGPTService;
    @MessageMapping("/categoria")
    @SendTo("/topic/categoria")
    public String getPreguntaOnline(String categoria) {
        if (Partida.isActiva()) {
            Partida.setCategoriaActual(categoria);
            return chatGPTService.getTriviaQuestion(categoria);
        }
        return "Error: No hay partida activa.";
    }

    @PostMapping("/terminar")
    public ResponseEntity<String> terminarPartida() {
        if (Partida.isActiva()) {
            Partida.reset();
            return ResponseEntity.ok("Partida terminada con éxito.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay ninguna partida activa.");
    }

    @MessageMapping("/respuesta")
    @SendTo("/topic/resultados")
    public String procesarRespuesta(String respuesta) {
        if (Partida.activa && Partida.getPreguntaActual() != null) {
            if (respuesta.equals(Partida.getRespuestaCorrecta())) {
                return "Correcto";
            } else {
                return "Incorrecto";
            }
        }
        return "Error: No hay partida activa.";
    }
}