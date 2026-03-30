package com.example.controlefinanceiroapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categorias")
@Data
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @OneToMany(mappedBy = "categoria")
    private List<Transacao> transacoes;

    @Deprecated
    public Categoria(){}

    public Categoria(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
