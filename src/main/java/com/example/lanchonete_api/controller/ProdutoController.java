package com.example.lanchonete_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.lanchonete_api.dto.ProdutoRequestDTO;
import com.example.lanchonete_api.dto.ProdutoResponseDTO;

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
    public ProdutoResponseDTO criar(
            @Valid @RequestBody ProdutoRequestDTO dto) {
        return service.salvar(dto);
    }

    // READ - listar todos
    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return service.listarTodos();
    }

    // READ - buscar por id
    @GetMapping("/{id}")
    public ProdutoResponseDTO buscar(@PathVariable Long id) {
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
    public ProdutoResponseDTO atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {
        return service.atualizar(id, dto);
    }

}
