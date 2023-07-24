package br.com.jornadamilhas.api.controller;

import br.com.jornadamilhas.api.controller.model.depoimento.DadosCadastroDepoimento;
import br.com.jornadamilhas.api.controller.model.depoimento.Depoimento;
import br.com.jornadamilhas.api.controller.model.depoimento.DepoimentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroDepoimento dados){
        /* Exemplo de Request Body
            {
                "nome": "Karen Moreira",
                "foto-url": "image.png",
                "depoimento": "Lorem ipsum dolor sit amet"
            }
         */
        repository.save(new Depoimento(dados));
    }
}
