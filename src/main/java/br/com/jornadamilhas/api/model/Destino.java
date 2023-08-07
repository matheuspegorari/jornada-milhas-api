package br.com.jornadamilhas.api.model;

import br.com.jornadamilhas.api.dto.destino.DadosAtualizacaoDestino;
import br.com.jornadamilhas.api.dto.destino.DadosCadastroDestino;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity(name = "Destinos")
@Table(name = "destinos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String imagem_url1;
    private String imagem_url2;
    private String nome;
    private String meta;
    private String texto;
    private BigDecimal preco;

    public Destino(DadosCadastroDestino dados) {
        this.imagem_url1 = dados.imagem_url1();
        this.imagem_url2 = dados.imagem_url2();
        this.nome = dados.nome();
        this.meta = dados.meta();
        this.texto = dados.texto();
        this.preco = dados.preco();
    }

    public void atualizar(@Valid DadosAtualizacaoDestino dados) {
        if(dados.imagem_url1() != null) this.imagem_url1 = dados.imagem_url1();
        if(dados.imagem_url2() != null) this.imagem_url2 = dados.imagem_url2();
        if(dados.nome() != null) this.nome = dados.nome();
        if(dados.meta() != null) this.meta = dados.meta();
        if(dados.texto() != null) this.texto = dados.texto();
        if(dados.preco() != null) this.preco = dados.preco();
    }
}
