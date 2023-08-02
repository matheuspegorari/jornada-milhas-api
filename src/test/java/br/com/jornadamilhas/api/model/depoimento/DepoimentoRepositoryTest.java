package br.com.jornadamilhas.api.model.depoimento;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // Anotação para testar repository
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DepoimentoRepositoryTest {

    @Autowired
    private DepoimentoRepository depoimentoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deve devolver null quando não houver nenhum cadastro no sistema.")
    void naoRetornarNada() {
        var retorno = depoimentoRepository.findTop3Rand();
        List<Depoimento> vazio = new ArrayList<>();
        assertThat(retorno).isEqualTo(vazio);
    }

    @Test
    @DisplayName("Deve retornar a lista com o objeto criado")
    void retornarApenasUm() {
        var depoimento = cadastrar(null, "fagundes", "teste.png", "Adorei", true);
        var retorno = depoimentoRepository.findTop3Rand();
        List<Depoimento> lista = new ArrayList<>();
        lista.add(depoimento);

        assertThat(retorno).isEqualTo(lista);
    }


    private Depoimento cadastrar(Long id, String nome, String imagem, String depo, boolean ativo){
        Depoimento depoimento = new Depoimento(id, nome, imagem,depo,ativo);
        em.persist(depoimento);
        return depoimento;

    }
}