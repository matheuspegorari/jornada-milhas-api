package br.com.jornadamilhas.api.dto.destino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record DadosCadastroDestino(

        @NotBlank
        String imagem_url1,
        @NotBlank
        String imagem_url2,
        @NotBlank
        String nome,
        @NotBlank
        @Size(max = 160)
        String meta,
        String texto,
        @NotNull
        BigDecimal preco
) {

}
