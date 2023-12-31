package br.com.jornadamilhas.api.model;

import br.com.jornadamilhas.api.dto.depoimento.DadosAtualizacaoDepoimento;
import br.com.jornadamilhas.api.dto.depoimento.DadosCadastroDepoimento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Depoimentos")
@Table(name="depoimentos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Depoimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imagem_url;
    private String depoimento;
    private boolean ativo;

    public Depoimento(DadosCadastroDepoimento dados) {
        this.nome = dados.nome();
        this.imagem_url = dados.imagem_url();
        this.depoimento = dados.depoimento();
        this.ativo = true;
    }

    public void atualizar(DadosAtualizacaoDepoimento dados) {
        if(dados.nome() != null) this.nome = dados.nome();
        if(dados.imagem_url() != null) this.imagem_url = dados.imagem_url();
        if(dados.depoimento() != null) this.depoimento = dados.depoimento();
    }


    public void excluir() {
        this.ativo = false;
    }
}


