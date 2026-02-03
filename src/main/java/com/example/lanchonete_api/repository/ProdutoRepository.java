package com.example.lanchonete_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lanchonete_api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> findAll(Pageable pageable);

    Page<Produto> findByCategoria(String categoria, Pageable pageable);

    Page<Produto> findByDisponivel(Boolean disponivel, Pageable pageable);

    Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
