package com.example.demo.Services;

import com.example.demo.Model.Jugador;
import com.example.demo.Repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public Optional<Jugador> obtenerJugadorPorNombre(String nombre) {
        return jugadorRepository.findByNombre(nombre);
    }

    public boolean jugadorExiste(String nombre) {
        return jugadorRepository.findByNombre(nombre).isPresent();
    }

    public boolean verificarCredenciales(String nombre, String contrasena) {
        // Buscar el usuario en la base de datos usando el nombre
        Optional<Jugador> jugadorOpt = jugadorRepository.findByNombre(nombre);

        // Verificar si el usuario existe y si la contrasena es correcta
        if (jugadorOpt.isPresent()) {
            Jugador jugador = jugadorOpt.get();

            // Aquí podrías usar un método para comparar contrasenas encriptadas
            return jugador.getContrasena().equals(contrasena);
        }

        return false; // Retorna false si el usuario no existe o si la contrasena es incorrecta
    }

    public void registrarJugador(Jugador jugador) {
        jugadorRepository.save(jugador);
    }
}
