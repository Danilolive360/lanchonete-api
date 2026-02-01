package com.example.lanchonete_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.lanchonete_api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
