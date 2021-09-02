package com.projetointegradorgrupo3.ProjetoReintegrar.controller;

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

import com.projetointegradorgrupo3.ProjetoReintegrar.model.Tema;
import com.projetointegradorgrupo3.ProjetoReintegrar.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class TemaController {

	@Autowired
	private TemaRepository repository;
	
	@GetMapping
    public ResponseEntity<List<Tema>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }
	
	@GetMapping("/tema/{tema}")
    public ResponseEntity<List<Tema>> GetByDescricao(@PathVariable String tema){
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(tema));
    }
	
	@PostMapping
    public ResponseEntity<Tema> post (@RequestBody Tema tema){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }

    @PutMapping
    public ResponseEntity<Tema> put (@RequestBody Tema tema){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        repository.deleteById(id);
    }
}