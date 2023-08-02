package br.com.jornadamilhas.api.controller;

import br.com.jornadamilhas.api.model.destinos.DadosAtualizacaoDestino;
import br.com.jornadamilhas.api.model.destinos.DadosCadastroDestino;
import br.com.jornadamilhas.api.model.destinos.Destino;
import br.com.jornadamilhas.api.model.destinos.DestinosRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinosController {

    @Autowired
    private DestinosRepository repository;

    @PostMapping
    @Transactional
    public void cadastrarDestino(@RequestBody DadosCadastroDestino dados){
        repository.save(new Destino(dados));
    }

    @GetMapping
    public List<Destino>  getAllDestinos(){
        return repository.findAll();
    }

    @GetMapping(params = "nome")
    public ResponseEntity<Object> getDestinoByNome(@RequestParam("nome") String nome){
        List<Destino> list = repository.findDestinoByNome(nome);
        if (list.isEmpty()) {
            return new ResponseEntity<>("Nenhum destino foi encontrado", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PutMapping
    @Transactional
    public void atualizarDestino(@RequestBody DadosAtualizacaoDestino dados){
        Destino destino = repository.getReferenceById(dados.id());
        destino.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    public void deletarDestino(@PathVariable("id") Long id){
        repository.deleteById(id);
    }


}
