//Controla as entradas e saídas da aplicação(EndPoints)

package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController //Define que essa classe será uma classe controladora
@RequestMapping("/postagens") // Define o endpoint padrão
@CrossOrigin(origins = "*", allowedHeaders = "*")
//Garante uma segurança para a aplicação, normalmente libera apenas alguns recursos
public class PostagemController {

    @Autowired //Delega ao spring a função de trazer os métodos.
    private PostagemRepository postagemRepository; // Com o Autowired traz os métodos de CRUD referentes a "PostagemRepository"

    @GetMapping // Mapeia o método para ele responder a solicitação do "GET"
    public ResponseEntity<List<Postagem>> getAll() {  //ResponseEntity é um pacote que busca as informações dentro do "<>" nesse caso a lista de postagens
        return ResponseEntity.ok(postagemRepository.findAll());//Ele não retorna a lista de postagem, e sim um ResponseEntity com a lista de postagens
    }

    @GetMapping("/{id}") // Função de buscar postagem no endpoint por ID
    public ResponseEntity<Postagem> getById(@PathVariable Long id) {
        return postagemRepository.findById(id)
                .map(postagem -> ResponseEntity.ok(postagem))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}") // Função de buscar postagem por "Titulo" no endpoint
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }



    @PostMapping // Função de postar novas tabelas direto do Insomnia com JSON.
    public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postagemRepository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {
        return postagemRepository.findById(postagem.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(postagemRepository.save(postagem)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);

        if (postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        postagemRepository.deleteById(id);
    }

}