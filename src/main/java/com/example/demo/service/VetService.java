package com.example.demo.service;

import com.example.demo.model.Veterinarian;
import com.example.demo.repository.VetRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VetService {
    private final VetRepository repo;
    public VetService(VetRepository repo) { this.repo = repo; }
    public List<Veterinarian> findAll() { return repo.findAll(); }
    public Veterinarian findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Veterinarian save(Veterinarian vet) { return repo.save(vet); }
    public void delete(Long id) { repo.deleteById(id); }
}
