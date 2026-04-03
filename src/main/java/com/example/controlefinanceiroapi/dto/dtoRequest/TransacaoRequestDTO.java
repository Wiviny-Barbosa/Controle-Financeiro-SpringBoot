package com.example.controlefinanceiroapi.dto.dtoRequest;

import com.example.controlefinanceiroapi.enumeracao.TipoEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record TransacaoRequestDTO(@NotBlank(message = "Descrição é obrigatório") String descricao,
                                  @NotNull(message = "Valor é obrigatório") @PositiveOrZero(message = "O valor tem que ser positivo") Double valor,
                                  @NotNull(message = "Tipo é obrigatório") TipoEnum tipo,
                                  @NotNull(message = "O id da conta é obrigatório") UUID contaId,
                                  @NotNull(message = "O id da categoria é obrigatório") UUID categoriaId) {
}
