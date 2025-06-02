package com.example.demo.service;

import com.example.demo.model.Pet;
import com.example.demo.repository.PetRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PetService {
    private final PetRepository repo;
    public PetService(PetRepository repo) { this.repo = repo; }
    public List<Pet> findAll() { return repo.findAll(); }
    public Pet findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Pet save(Pet pet) { return repo.save(pet); }
    public void delete(Long id) { repo.deleteById(id); }
}
