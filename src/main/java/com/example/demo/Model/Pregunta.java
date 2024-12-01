package com.example.demo.Model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Pregunta {
    @Id
    @Column(nullable = false)
    private String enunciado;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String respuestaCorrecta;

    @ElementCollection
    private List<String> respuestasIncorrectas;

    public Pregunta() {
    }

    public Pregunta(String enunciado, String categoria, String respuestaCorrecta, List<String> respuestasIncorrectas) {
        this.enunciado = enunciado;
        this.categoria = categoria;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestasIncorrectas = respuestasIncorrectas;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public List<String> getRespuestasIncorrectas() {
        return respuestasIncorrectas;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public void setRespuestasIncorrectas(List<String> respuestasIncorrectas) {
        this.respuestasIncorrectas = respuestasIncorrectas;
    }


}
