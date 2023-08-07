package br.com.jornadamilhas.api.dto.destino;

import br.com.jornadamilhas.api.model.Destino;


public record DadosListagemDestino(
        String imagem_url1,
        String imagem_url2,
        String nome,
        String meta,
        String texto
) {
    public DadosListagemDestino(Destino destino){
        this(destino.getImagem_url1(),
                destino.getImagem_url2(),
                destino.getNome(),
                destino.getMeta(),
                destino.getTexto());
    }
}
