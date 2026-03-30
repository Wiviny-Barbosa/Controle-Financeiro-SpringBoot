package com.example.controlefinanceiroapi.dto.dtoResponse;

import com.example.controlefinanceiroapi.enumeracao.TipoEnum;
import com.example.controlefinanceiroapi.model.Categoria;
import com.example.controlefinanceiroapi.model.Conta;

import java.util.Date;
import java.util.UUID;

public record TransacaoResponseDTO(UUID id, String descricao, double valor, TipoEnum tipo, Date date, Conta conta, Categoria categoria) {
}
