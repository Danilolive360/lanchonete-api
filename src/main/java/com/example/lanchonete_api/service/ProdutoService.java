package com.example.lanchonete_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lanchonete_api.model.Produto;
import com.example.lanchonete_api.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
// Lógica de negócio relacionada a produtos
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto com id " + id + " não encontrado"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // Atualiza um produto existente
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produto = repository.findById(id).orElse(null);

        if (produto == null) {
            return null;
        }

        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setDisponivel(produtoAtualizado.getDisponivel());

        return repository.save(produto);

    }

}
