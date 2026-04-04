package com.example.controlefinanceiroapi.model;

import com.example.controlefinanceiroapi.enumeracao.TipoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transacoes")
@Data
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String descricao;
    private double valor;
    @Enumerated(EnumType.STRING)
    private TipoEnum tipo;
    private Date data;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Deprecated
    public Transacao(){}

    public Transacao(UUID id, String descricao, double valor, Date data, TipoEnum tipo, Conta conta, Categoria categoria) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.conta = conta;
        this.categoria = categoria;
    }

    public double aplicarEfeitoNoSaldo(double saldoConta) {
        if (this.tipo == TipoEnum.ENTRADA) {
            return saldoConta + this.valor;
        }
        return saldoConta - this.valor;
    }

    public double reverterEfeitoNoSaldo(double saldoConta) {
        if (this.tipo == TipoEnum.ENTRADA) {
            return saldoConta - this.valor;
        }
        return saldoConta + this.valor;
    }
}
