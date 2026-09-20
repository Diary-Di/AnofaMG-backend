package com.example.backend.repository;

import com.example.backend.entity.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepository extends JpaRepository<Ville, String> {
}