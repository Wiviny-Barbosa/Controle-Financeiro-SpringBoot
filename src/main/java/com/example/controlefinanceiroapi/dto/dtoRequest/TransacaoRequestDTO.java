package com.example.controlefinanceiroapi.dto.dtoRequest;

import com.example.controlefinanceiroapi.enumeracao.TipoEnum;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

public record TransacaoRequestDTO(String descricao,double valor, TipoEnum tipo, UUID contaId, UUID categoriaId) {
}
