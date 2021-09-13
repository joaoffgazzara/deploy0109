package com.projetointegradorgrupo3.ProjetoReintegrar.repository;

import com.projetointegradorgrupo3.ProjetoReintegrar.model.Tema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TemaRepositoryTest {

    @Autowired
    private  TemaRepository repository;

    @Test
    public void deveriaCarregarTemasPesquisandoPorNome(){

        List<Tema> temas = repository.findAllByNomeContainingIgnoreCase("tecnologia");

        Assertions.assertEquals("tecnologia", temas.get(0).getNome());
    }

    @Test
    public void deveriaCarregarUmaListaVaziaAoProcurarUmTemaQueNaoExiste(){

        List<Tema> listaVazia = repository.findAllByNomeContainingIgnoreCase("culinaria");

        Assertions.assertEquals(0, listaVazia.size());
    }
}
