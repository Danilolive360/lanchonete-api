package com.example.lanchonete_api.service;

// Serviço para gerenciar produtos
import java.util.List;

// Importações necessárias
import org.springframework.stereotype.Service;

import com.example.lanchonete_api.dto.ProdutoRequestDTO;
import com.example.lanchonete_api.dto.ProdutoResponseDTO;
// Importações de classes do projeto
import com.example.lanchonete_api.model.Produto;
import com.example.lanchonete_api.repository.ProdutoRepository;

// Exceção para entidade não encontrada

// Anotação de serviço do Spring
@Service
// Lógica de negócio relacionada a produtos
public class ProdutoService {

    // Repositório para operações de banco de dados
    private final ProdutoRepository repository;

    // Construtor para injeção de dependência do repositório
    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // Salva um novo produto no banco de dados
    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setDescricao(dto.getDescricao());
        produto.setDisponivel(dto.getDisponivel());

        Produto salvo = repository.save(produto);

        return new ProdutoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getPreco(),
                salvo.getCategoria(),
                salvo.getDescricao(),
                salvo.getDisponivel());
    }

    // Retorna uma lista de todos os produtos
    public List<ProdutoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(p -> new ProdutoResponseDTO(
                        p.getId(),
                        p.getNome(),
                        p.getPreco(),
                        p.getCategoria(),
                        p.getDescricao(),
                        p.getDisponivel()))
                .toList();
    }

    // Busca um produto pelo seu ID
    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria(),
                produto.getDescricao(),
                produto.getDisponivel());
    }

    // Deleta um produto pelo seu ID
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // Atualiza um produto existente
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {

        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setDescricao(dto.getDescricao());
        produto.setDisponivel(dto.getDisponivel());

        Produto atualizado = repository.save(produto);

        return new ProdutoResponseDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getPreco(),
                atualizado.getCategoria(),
                atualizado.getDescricao(),
                atualizado.getDisponivel());
    }

}
