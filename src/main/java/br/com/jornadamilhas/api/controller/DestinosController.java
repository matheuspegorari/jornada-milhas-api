package br.com.jornadamilhas.api.controller;

import br.com.jornadamilhas.api.dto.destino.DadosAtualizacaoDestino;
import br.com.jornadamilhas.api.dto.destino.DadosCadastroDestino;
import br.com.jornadamilhas.api.dto.destino.DadosListagemDestino;
import br.com.jornadamilhas.api.exception.ErrorMessage;
import br.com.jornadamilhas.api.integration.ChatGPTIntegrationService;
import br.com.jornadamilhas.api.model.Destino;
import br.com.jornadamilhas.api.repository.DestinosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/destinos")
public class DestinosController {

    private final DestinosRepository repository;
    private final ChatGPTIntegrationService gpt = new ChatGPTIntegrationService();

    public DestinosController(DestinosRepository repository) {
        this.repository = repository;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<Destino> cadastrarDestino(@RequestBody @Valid DadosCadastroDestino dados) {
        String texto = dados.texto();

        if (StringUtils.isEmpty(texto)) {
            texto = StringUtils.trim(gpt.geraTextoDestino(dados.nome()));
            DadosCadastroDestino novosDados = new DadosCadastroDestino(
                    dados.imagem_url1(),
                    dados.imagem_url2(),
                    dados.nome(),
                    dados.meta(),
                    texto,
                    dados.preco()
            );
            var destino = repository.save(new Destino(novosDados));
            return new ResponseEntity<>(destino, HttpStatus.CREATED);
        }
        var destino = repository.save(new Destino(dados));
        return new ResponseEntity<>(destino, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Destino>  getAllDestinos(){
        return repository.findAll();
    }

    @GetMapping(params = "nome")
    public ResponseEntity<Object> getDestinoByNome(@RequestParam("nome") String nome) {
        List<Destino> list = repository.findDestinoByNome(nome);
        if (list.isEmpty()) {
            return new ResponseEntity<>(new ErrorMessage("Nenhum destino foi encontrado"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDestinoById(@PathVariable Long id) {
        Destino destino = repository.findDestinoById(id);
        if (destino == null) {
            return new ResponseEntity<>(new ErrorMessage("Nenhum destino foi encontrado"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                new DadosListagemDestino(destino),
                HttpStatus.OK);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> atualizarDestino(@RequestBody DadosAtualizacaoDestino dados) {
        Destino destino = repository.findDestinoById(dados.id());
        if (destino == null){
            return new ResponseEntity<>(new ErrorMessage("Nenhum destino foi encontrado"), HttpStatus.NOT_FOUND);
        }
        destino.atualizar(dados);
        return new ResponseEntity<>(destino, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDestino(@PathVariable("id") Long id) {
        if (repository.findDestinoById(id) == null){
            return new ResponseEntity<>(
                    new ErrorMessage("Nenhum destino foi encontrado"),
                    HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
