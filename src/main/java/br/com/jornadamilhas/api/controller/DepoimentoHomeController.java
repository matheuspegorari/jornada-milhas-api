package br.com.jornadamilhas.api.controller;

import br.com.jornadamilhas.api.model.depoimento.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
