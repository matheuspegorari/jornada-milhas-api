package br.com.jornadamilhas.api.controller;

import br.com.jornadamilhas.api.model.depoimento.DadosAtualizacaoDepoimento;
import br.com.jornadamilhas.api.model.depoimento.DadosCadastroDepoimento;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class DepoimentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroDepoimento> dadosCadastroDepoimentoJacksonTester;

    @Autowired
    private JacksonTester<DadosAtualizacaoDepoimento> dadosAtualizacaoDepoimentoJacksonTester;

    @Test
    @DisplayName("Deve dar o erro 400, pois nao enviou o body")
    void cadastrar400BADREQUEST() throws Exception {
        var response = mvc.perform(post("/depoimentos"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deve retornar OK, pois foi enviado body correto")
    void cadastrar200OK() throws Exception {
        var response = mvc
                .perform(post("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosCadastroDepoimentoJacksonTester.write(
                                new DadosCadastroDepoimento("Mariana", "mariana.jpg", "Testando 123")
                        ).getJson())
                )
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Deve retornar 200, pois não é necessário nenhum argumento")
    void listar() throws Exception {
        var response = mvc.perform(get("/depoimentos"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("400 - Não passa nenhum parametro")
    void atualizar400() throws Exception {
        var response = mvc.perform(put("/depoimentos"))
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("200 - Body correto")
    void atualizar200() throws Exception {
        var response = mvc
                .perform(put("/depoimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosAtualizacaoDepoimentoJacksonTester.write(
                                new DadosAtualizacaoDepoimento(1L, "Julia", null, null)
                        ).getJson())
                )
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Exclusão realizada com sucesso")
    void excluir() throws Exception {
        long idToDelete = 4L;

        var response = mvc
                .perform(delete("/depoimentos/{id}", idToDelete)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn().getResponse();

        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}


