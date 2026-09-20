package com.example.backend.controller;

import com.example.backend.entity.Annonce;
import com.example.backend.repository.AnnonceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {

    private final AnnonceRepository annonceRepository;

    public AnnonceController(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    // READ - all annonces
    @GetMapping
    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    // READ - one annonce
    @GetMapping("/{id}")
    public Annonce getAnnonce(@PathVariable Integer id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Annonce not found"));
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Annonce createAnnonce(@RequestBody Annonce annonce) {
        return annonceRepository.save(annonce);
    }
}