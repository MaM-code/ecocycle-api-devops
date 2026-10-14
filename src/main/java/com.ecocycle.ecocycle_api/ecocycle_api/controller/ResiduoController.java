package com.ecocycle.ecocycle_api.ecocycle_api.controller;

import com.ecocycle.ecocycle_api.ecocycle_api.entity.Residuo;
import com.ecocycle.ecocycle_api.ecocycle_api.repository.ResiduoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/residuos")
public class ResiduoController {

    private final ResiduoRepository repository;

    public ResiduoController(ResiduoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Residuo criar(@RequestBody Residuo residuo) {
        return repository.save(residuo);
    }

    @GetMapping
    public List<Residuo> listar() {
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public Residuo atualizar(@PathVariable Long id,
                             @RequestBody Residuo residuoAtualizado) {

        Residuo residuo = repository.findById(id).orElseThrow();

        residuo.setTipo(residuoAtualizado.getTipo());
        residuo.setPeso(residuoAtualizado.getPeso());
        residuo.setDestino(residuoAtualizado.getDestino());

        return repository.save(residuo);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}