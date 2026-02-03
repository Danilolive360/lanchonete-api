package com.example.lanchonete_api.service;

// Serviço para gerenciar produtos
import java.util.List;

// Importações necessárias
import org.springframework.stereotype.Service;
// Importações de classes do projeto
import com.example.lanchonete_api.dto.ProdutoRequestDTO;
import com.example.lanchonete_api.dto.ProdutoResponseDTO;

// Importações do modelo e repositório
import com.example.lanchonete_api.model.Produto;
import com.example.lanchonete_api.repository.ProdutoRepository;

// Importações de paginação
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    // Lista produtos de forma paginada
    public Page<ProdutoResponseDTO> listarPaginado(Pageable pageable) {

        return repository.findAll(pageable)
                .map(produto -> new ProdutoResponseDTO(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getCategoria(),
                        produto.getDescricao(),
                        produto.getDisponivel()));
    }

    // Busca produtos com filtros opcionais e paginação
    public Page<ProdutoResponseDTO> buscar(
            String categoria,
            Boolean disponivel,
            String nome,
            Pageable pageable) {

        Page<Produto> page;

        if (categoria != null) {
            page = repository.findByCategoria(categoria, pageable);
        } else if (disponivel != null) {
            page = repository.findByDisponivel(disponivel, pageable);
        } else if (nome != null) {
            page = repository.findByNomeContainingIgnoreCase(nome, pageable);
        } else {
            page = repository.findAll(pageable);
        }

        return page.map(p -> new ProdutoResponseDTO(
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getCategoria(),
                p.getDescricao(),
                p.getDisponivel()));
    }

}
