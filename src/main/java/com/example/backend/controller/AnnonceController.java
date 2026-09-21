package com.example.backend.controller;

import com.example.backend.entity.Annonce;
import com.example.backend.service.AnnonceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annonces")
@CrossOrigin(originPatterns = {
        "http://localhost:5173",
        "http://10.185.162.207:5173",
        "https://*.ts.net",
        "https://*.ngrok-free.dev",
        "https://*.ngrok-free.app",
        "https://*.ngrok.app",
        "https://*.ngrok.io"
})
public class AnnonceController {

    private final AnnonceService annonceService;

    public AnnonceController(AnnonceService annonceService) {
        this.annonceService = annonceService;
    }

    // READ - all annonces
    @GetMapping
    public List<AnnonceListResponse> getAllAnnonces() {
        return annonceService.getAllAnnonces().stream()
                .map(AnnonceController::toListResponse)
                .toList();
    }

    // READ - one annonce
    @GetMapping("/{id}")
    public Annonce getAnnonce(@PathVariable Integer id) {
        return annonceService.getAnnonce(id);
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Annonce createAnnonce(@RequestBody Annonce annonce) {
        return annonceService.createAnnonce(annonce);
    }

    private static AnnonceListResponse toListResponse(Annonce annonce) {
        List<String> images = annonce.getImages() == null
                ? List.of()
                : annonce.getImages();

        VilleListResponse ville = annonce.getVille() == null
                ? null
                : new VilleListResponse(
                        annonce.getVille().getBoitePostal(),
                        annonce.getVille().getDesignation());

        return new AnnonceListResponse(
                annonce.getNumId(),
                annonce.getTitre(),
                annonce.getTypeBatiment(),
                ville,
                annonce.getAdresse(),
                annonce.getPrix(),
                annonce.getChambre(),
                annonce.getSalleDeBain(),
                images);
    }

    public record AnnonceListResponse(
            Integer numId,
            String titre,
            String typeBatiment,
            VilleListResponse ville,
            String adresse,
            Integer prix,
            Integer chambre,
            Integer salleDeBain,
            List<String> images) {
    }

    public record VilleListResponse(String boitePostal, String designation) {
    }
}