package com.example.controlefinanceiroapi.dto.dtoRequest;

import com.example.controlefinanceiroapi.enumeracao.TipoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

public record TransacaoRequestDTO(@NotBlank(message = "Descrição é obrigatório") String descricao,
                                  @NotBlank(message = "Valor é obrigatório") @PositiveOrZero(message = "O valor tem que ser positivo") double valor,
                                  @NotBlank(message = "Tipo é obrigatório") TipoEnum tipo,
                                  @NotBlank(message = "O id da conta é obrigatório") UUID contaId,
                                  @NotBlank(message = "O id da categoria é obrigatório") UUID categoriaId) {
}
