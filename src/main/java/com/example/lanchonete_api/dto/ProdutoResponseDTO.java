package com.example.lanchonete_api.dto;

public class ProdutoResponseDTO {

    private Long id;
    private String nome;
    private Double preco;
    private String categoria;
    private String descricao;
    private Boolean disponivel;

    public ProdutoResponseDTO(Long id, String nome, Double preco, String categoria, String descricao,
            Boolean disponivel) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
        this.disponivel = disponivel;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public Double getPreco() {
        return preco;
    }
}
