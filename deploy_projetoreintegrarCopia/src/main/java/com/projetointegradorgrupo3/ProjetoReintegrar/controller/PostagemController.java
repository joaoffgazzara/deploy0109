package com.projetointegradorgrupo3.ProjetoReintegrar.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetointegradorgrupo3.ProjetoReintegrar.model.Postagem;
import com.projetointegradorgrupo3.ProjetoReintegrar.repository.PostagemRepository;

@RestController
@RequestMapping("/postagem")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class PostagemController {
	@Autowired
    private PostagemRepository repository;

    @GetMapping
    public ResponseEntity<List<Postagem>> findAll(){
        return ResponseEntity.ok(repository.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> GetById(@PathVariable long id){
        return repository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }
    
    @GetMapping("/data/{data}")
    public ResponseEntity<List<Postagem>> GetByData(@PathVariable Date data){
        return ResponseEntity.ok(repository.findAllByData(data));
    }
    
    @GetMapping("/corpo/{corpo}")
    public ResponseEntity<List<Postagem>> GetByCorpo(@PathVariable String corpo){
        return ResponseEntity.ok(repository.findAllByCorpoContainingIgnoreCase(corpo));
    }

    @PostMapping
    public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }
}
