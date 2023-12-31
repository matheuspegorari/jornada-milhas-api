package br.com.jornadamilhas.api.dto.depoimento;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroDepoimento(
        @NotBlank
        String nome,
        @NotBlank
        String imagem_url,
        @NotBlank
        String depoimento
) {
}
