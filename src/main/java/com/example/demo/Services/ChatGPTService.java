package com.example.demo.Services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String apiKey; // API Key de OpenAI

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String getTriviaQuestion(String category) {
        // Prompt para la pregunta con una instrucción para generar respuestas aleatorias
        String prompt = String.format(
                "Genera una pregunta al azar de la categoría \"%s\" para un juego de preguntas y respuestas. " +
                        "Incluye la pregunta, una respuesta correcta y tres respuestas incorrectas en formato JSON, como este ejemplo: " +
                        "{ \"pregunta\": \"¿En qué año comenzó la Segunda Guerra Mundial?\", \"correcta\": \"1939\", \"incorrectas\": [\"1941\", \"1935\", \"1945\"] }",
                category
        );

        // Crear el cuerpo de la solicitud
        String requestBody = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [\n" +
                "    { \"role\": \"user\", \"content\": \"" + prompt.replace("\"", "\\\"") + "\" }\n" +
                "  ]\n" +
                "}";

        // Configurar los encabezados
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // Configurar la solicitud
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Enviar la solicitud
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

            // Procesar la respuesta
            String responseBody = response.getBody();
            if (responseBody != null) {
                JSONObject jsonResponse = new JSONObject(responseBody);
                String content = jsonResponse.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
                return content; // Devuelve la pregunta y respuestas en formato JSON
            } else {
                return "No se recibió respuesta de la API.";
            }
        } catch (HttpClientErrorException e) {
            // Manejo de errores HTTP
            return "Error en la solicitud: " + e.getResponseBodyAsString();
        } catch (Exception e) {
            // Manejo de excepciones generales
            return "Error inesperado: " + e.getMessage();
        }


    }

}
