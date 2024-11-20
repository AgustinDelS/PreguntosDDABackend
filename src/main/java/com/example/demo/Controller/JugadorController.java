package com.example.demo.Controller;

import com.example.demo.Model.Jugador;
import com.example.demo.Services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;

    @GetMapping("/{nombre}")
    public ResponseEntity<?> obtenerJugador(@PathVariable String nombre) {
        return jugadorService.obtenerJugadorPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String nombre, @RequestParam String contrasena) {
        if (jugadorService.verificarCredenciales(nombre, contrasena)) {
            // Obtener el jugador por el nombre
            Optional<Jugador> jugadorOpt = jugadorService.obtenerJugadorPorNombre(nombre);
            if (jugadorOpt.isPresent()) {
                Jugador jugadorLogeado = jugadorOpt.get();
                return ResponseEntity.ok(jugadorLogeado); // Devuelve
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login inválido");
    }


    @PostMapping("/registrar")
    public ResponseEntity<Jugador> registrarJugador(@RequestParam String nombre, @RequestParam String contrasena) {
        if (jugadorService.jugadorExiste(nombre)) {
            // Devuelve un ResponseEntity con estado BAD_REQUEST y cuerpo vacío
            return ResponseEntity.badRequest().build();
        }

        // Crea y guarda el nuevo jugador
        Jugador jugador = new Jugador(nombre, contrasena);
        jugadorService.registrarJugador(jugador);

        // Devuelve el jugador registrado con un estado 200
        return ResponseEntity.ok(jugador);
    }

}
