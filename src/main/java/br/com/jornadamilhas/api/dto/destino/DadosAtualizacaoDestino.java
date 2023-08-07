package br.com.jornadamilhas.api.dto.destino;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DadosAtualizacaoDestino(
        @NotNull
        Long id,
        String imagem_url1,
        String imagem_url2,
        String nome,

        @Size(max = 160)
        String meta,
        String texto,
        BigDecimal preco
) {
}
