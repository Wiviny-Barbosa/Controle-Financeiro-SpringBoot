package com.example.controlefinanceiroapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;
    private String senha;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Conta> contas;

    @Deprecated
    public Usuario(){}

    public Usuario(UUID id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

}
