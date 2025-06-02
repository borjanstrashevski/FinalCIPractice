package com.example.demo.repository;

import com.example.demo.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VetRepository extends JpaRepository<Veterinarian, Long> {}
