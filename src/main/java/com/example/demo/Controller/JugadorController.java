package com.example.demo.Controller;

import com.example.demo.Model.Jugador;
import com.example.demo.Services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> login(@RequestParam String nombre,@RequestParam String contrasena) {
        if (jugadorService.verificarCredenciales(nombre, contrasena)) {
            return ResponseEntity.ok("exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login inválido");
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarJugador(@RequestBody Jugador jugador) {
        if (jugadorService.jugadorExiste(jugador.getUsuario())) {

            return ResponseEntity.badRequest().body("El nombre ya está en uso");
        }
        jugadorService.registrarJugador(jugador);
        return ResponseEntity.ok("Registro exitoso");
    }
}
