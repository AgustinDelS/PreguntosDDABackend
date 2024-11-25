package com.example.demo.Controller;

import com.example.demo.Services.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trivia")
public class TriviaController {

    @Autowired
    private ChatGPTService chatGPTService;

    @GetMapping("/pregunta")
    public String getPregunta(@RequestParam String categoria) {
        return chatGPTService.getTriviaQuestion(categoria);
    }


}
