package com.example.controlefinanceiroapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "contas")
@Data
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Deprecated
    public Conta(){}

    public Conta(UUID id, String nome, Double saldo, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
        this.usuario = usuario;
    }

}
