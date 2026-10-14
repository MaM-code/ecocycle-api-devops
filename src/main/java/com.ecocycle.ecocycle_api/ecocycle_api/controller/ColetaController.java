package com.ecocycle.ecocycle_api.ecocycle_api.controller;

import com.ecocycle.ecocycle_api.ecocycle_api.entity.Coleta;
import com.ecocycle.ecocycle_api.ecocycle_api.repository.ColetaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coletas")
public class ColetaController {

    private final ColetaRepository repository;

    public ColetaController(ColetaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Coleta criar(@RequestBody Coleta coleta) {
        return repository.save(coleta);
    }

    @GetMapping
    public List<Coleta> listar() {
        return repository.findAll();
    }

    @PutMapping("/{id}/status")
    public Coleta atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        Coleta coleta = repository.findById(id).orElseThrow();
        coleta.setStatus(status);
        return repository.save(coleta);
    }
}