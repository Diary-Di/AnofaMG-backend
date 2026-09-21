package com.example.backend.controller;

import com.example.backend.entity.Annonce;
import com.example.backend.event.AnnonceCreatedEvent;
import com.example.backend.service.AnnonceService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TransactionWebSocketNotifier {

    private final AnnonceService annonceService;
    private final SimpMessagingTemplate messagingTemplate;

    public TransactionWebSocketNotifier(AnnonceService annonceService,
            SimpMessagingTemplate messagingTemplate) {
        this.annonceService = annonceService;
        this.messagingTemplate = messagingTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void notifyAnnonceCreated(AnnonceCreatedEvent event) {
        Annonce annonce = annonceService.getAnnonce(event.annonceId());
        messagingTemplate.convertAndSend(
                "/topic/transactions",
                new TransactionNotification(
                        "INSERT",
                        annonce.getNumId(),
                        "Annonce créée avec succès",
                        annonce.getTitre(),
                        annonce.getEmail()));
    }

    public record TransactionNotification(
            String operation,
            Integer annonceId,
            String text,
            String titre,
            String email) {
    }
}