package com.example.lanchonete_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lanchonete_api.model.Produto;
import com.example.lanchonete_api.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Produto criar(@Valid @RequestBody Produto produto) {
        return service.salvar(produto);
    }

    // READ - listar todos
    @GetMapping
    public List<Produto> listar() {
        return service.listarTodos();
    }

    // READ - buscar por id
    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @RequestBody Produto produto) {

        Produto atualizado = service.atualizar(id, produto);

        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(atualizado);
    }

}
