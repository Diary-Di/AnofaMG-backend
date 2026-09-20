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
@CrossOrigin(origins = "http://localhost:5173")
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