package com.example.lanchonete_api.controller;

// Controlador REST para gerenciar produtos
import java.util.List;
// Importações necessárias
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Importações de classes do projeto
import com.example.lanchonete_api.dto.ProdutoRequestDTO;
import com.example.lanchonete_api.dto.ProdutoResponseDTO;

// Importações do serviço
import com.example.lanchonete_api.service.ProdutoService;

// Importações para validação
import jakarta.validation.Valid;

// Importações de paginação
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

// Importações para documentação da API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// Anotação de controlador REST do Spring
@RestController
@RequestMapping("/produtos")

@Tag(name = "Produtos", description = "Operações relacionadas a produtos")

// Controlador para endpoints relacionados a produtos
public class ProdutoController {

    // Serviço para lógica de negócio relacionada a produtos
    private final ProdutoService service;

    // Construtor para injeção de dependência do serviço
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    // CREATE
    @Operation(summary = "Criar produto", description = "Cadastra um novo produto")
    @PostMapping
    public ProdutoResponseDTO criar(
            @Valid @RequestBody ProdutoRequestDTO dto) {
        return service.salvar(dto);
    }

    // READ - listar todos
    @Operation(summary = "Listar produtos", description = "Retorna a lista de produtos cadastrados")
    @GetMapping
    public List<ProdutoResponseDTO> listar() {
        return service.listarTodos();
    }

    // READ - busca com filtros
    @GetMapping("/busca")
    public Page<ProdutoResponseDTO> buscar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Boolean disponivel,
            @RequestParam(required = false) String nome,
            Pageable pageable) {

        return service.buscar(categoria, disponivel, nome, pageable);
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

    // READ - listar paginado
    @GetMapping("/paginado")
    public Page<ProdutoResponseDTO> listarPaginado(
            @PageableDefault(size = 5, sort = "nome") Pageable pageable) {

        return service.listarPaginado(pageable);
    }

}
