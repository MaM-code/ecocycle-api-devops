package com.ecocycle.ecocycle_api.ecocycle_api.controller;

import com.ecocycle.ecocycle_api.ecocycle_api.entity.Usuario;
import com.ecocycle.ecocycle_api.ecocycle_api.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }
}