package com.example.backend.controller;

import com.example.backend.entity.Annonce;
import com.example.backend.service.AnnonceService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private final AnnonceService annonceService;

    public WebSocketController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    @MessageMapping("/annonces")
    public void createAnnonce(Annonce annonce) {
        annonceService.createAnnonce(annonce);
    }
}
