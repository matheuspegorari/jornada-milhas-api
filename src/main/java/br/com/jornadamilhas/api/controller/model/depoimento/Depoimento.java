package br.com.jornadamilhas.api.controller.model.depoimento;

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

    public Depoimento(DadosCadastroDepoimento dados) {
        this.nome = dados.nome();
        this.imagem_url = dados.imagem_url();
        this.depoimento = dados.depoimento();
    }
}


