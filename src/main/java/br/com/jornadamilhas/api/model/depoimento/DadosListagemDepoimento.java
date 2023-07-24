package br.com.jornadamilhas.api.model.depoimento;

public record DadosListagemDepoimento(
        Long id,
        String nome,
        String imagem_url,
        String depoimento
) {
    public DadosListagemDepoimento(Depoimento depo) {
        this(depo.getId(), depo.getNome(), depo.getImagem_url(), depo.getDepoimento());
    }
}
