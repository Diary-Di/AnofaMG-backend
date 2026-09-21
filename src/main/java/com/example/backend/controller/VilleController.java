package com.example.backend.controller;

import com.example.backend.entity.Ville;
import com.example.backend.repository.VilleRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/villes")
@CrossOrigin(originPatterns = {
        "http://localhost:5173",
        "http://10.185.162.207:5173",
        "https://*.ts.net",
        "https://*.ngrok-free.dev",
        "https://*.ngrok-free.app",
        "https://*.ngrok.app",
        "https://*.ngrok.io"
})
public class VilleController {

    private final VilleRepository villeRepository;

    public VilleController(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @GetMapping
    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }
}