package br.com.jornadamilhas.api.controller;

import br.com.jornadamilhas.api.model.Depoimento;
import br.com.jornadamilhas.api.repository.DepoimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/depoimentos-home")
public class DepoimentoHomeController {

    @Autowired
    private DepoimentoRepository repository;

    @GetMapping
    public List<Depoimento> depoimentoListRandom() {
        return repository.findTop3Rand();
    }

}
