package br.com.jornadamilhas.api.model.destinos;

import jakarta.persistence.*;
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
    private String nome;
    private String imagem_url;
    private BigDecimal preco;

    public Destino(DadosCadastroDestino dados) {
        this.nome = dados.nome();
        this.imagem_url = dados.imagem_url();
        this.preco = dados.preco();
    }

    public void atualizar(DadosAtualizacaoDestino dados) {
        if(dados.nome() != null) this.nome = dados.nome();
        if(dados.imagem_url() != null) this.imagem_url = dados.imagem_url();
        if(dados.preco() != null) this.preco = dados.preco();
    }
}
