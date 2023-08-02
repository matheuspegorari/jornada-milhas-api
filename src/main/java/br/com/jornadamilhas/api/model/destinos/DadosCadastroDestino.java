package br.com.jornadamilhas.api.model.destinos;

import java.math.BigDecimal;

public record DadosCadastroDestino (
        String nome,
        String imagem_url,
        BigDecimal preco
){

}
