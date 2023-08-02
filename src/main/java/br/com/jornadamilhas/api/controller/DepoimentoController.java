package br.com.jornadamilhas.api.controller;

import br.com.jornadamilhas.api.model.depoimento.DadosAtualizacaoDepoimento;
import br.com.jornadamilhas.api.model.depoimento.DadosListagemDepoimento;
import br.com.jornadamilhas.api.model.depoimento.DadosCadastroDepoimento;
import br.com.jornadamilhas.api.model.depoimento.Depoimento;
import br.com.jornadamilhas.api.model.depoimento.DepoimentoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/depoimentos")
public class DepoimentoController {

    @Autowired
    private DepoimentoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroDepoimento dados){
        repository.save(new Depoimento(dados));
    }

    @GetMapping
    public Page<DadosListagemDepoimento> listar(Pageable pageable){
        return repository
                .findAllByAtivoTrue(pageable)
                .map(DadosListagemDepoimento::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoDepoimento dados){
        Depoimento depoimento = repository.getReferenceById(dados.id());
        depoimento.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Long id){
        Depoimento depoimento = repository.getReferenceById(id);
        depoimento.excluir();
    }

}
