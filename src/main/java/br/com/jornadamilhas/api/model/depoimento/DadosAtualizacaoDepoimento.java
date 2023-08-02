package br.com.jornadamilhas.api.model.depoimento;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoDepoimento(
        @NotNull
        Long id,
        String nome,
        String imagem_url,
        String depoimento
) {
}
