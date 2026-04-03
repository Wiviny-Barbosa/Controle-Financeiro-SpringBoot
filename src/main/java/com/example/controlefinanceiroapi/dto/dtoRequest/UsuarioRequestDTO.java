package com.example.controlefinanceiroapi.dto.dtoRequest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioRequestDTO(@NotBlank(message = "Nome é obrigatório") String nome,
                                @Email(message = "Email tem que ser valido") String email,
                                @NotBlank(message = "Senha não pode ser nula") @Length(min = 6, max = 15) String senha) {
}
