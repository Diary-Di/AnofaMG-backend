package com.example.backend.service;

import com.example.backend.entity.Annonce;
import com.example.backend.event.AnnonceCreatedEvent;
import com.example.backend.repository.AnnonceRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnonceService {

    private final AnnonceRepository annonceRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AnnonceService(AnnonceRepository annonceRepository,
            ApplicationEventPublisher eventPublisher) {
        this.annonceRepository = annonceRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional(readOnly = true)
    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Annonce getAnnonce(Integer id) {
        return annonceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Annonce not found"));
    }

    @Transactional
    public Annonce createAnnonce(Annonce annonce) {
        Annonce savedAnnonce = annonceRepository.saveAndFlush(annonce);
        eventPublisher.publishEvent(new AnnonceCreatedEvent(savedAnnonce.getNumId()));
        return savedAnnonce;
    }
}