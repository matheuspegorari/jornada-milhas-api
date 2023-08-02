package br.com.jornadamilhas.api.model.destinos;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DadosAtualizacaoDestino(
        @NotNull
        Long id,
        String nome,
        String imagem_url,
        BigDecimal preco
) {
}
