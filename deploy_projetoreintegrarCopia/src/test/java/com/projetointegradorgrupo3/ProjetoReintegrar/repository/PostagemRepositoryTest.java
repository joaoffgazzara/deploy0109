package com.projetointegradorgrupo3.ProjetoReintegrar.repository;

import com.projetointegradorgrupo3.ProjetoReintegrar.model.Postagem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PostagemRepositoryTest {

    @Autowired
    private PostagemRepository repository;

    @Test
    public void deveriaProcurarPostagemPorTitulo(){
        List<Postagem> postagensComOla = repository.findAllByTituloContainingIgnoreCase("Olá");

        Assertions.assertTrue(postagensComOla.size() > 0);
    }

    @Test
    public void deveriaProcurarPostagemPorCorpo(){
        List<Postagem> postagensComComo = repository.findAllByCorpoContainingIgnoreCase("como");

        Assertions.assertTrue(postagensComComo.size() > 0);
    }

    @Test
    public void NaodeveriaCarregarUmaPostagemComDataErrada(){

        Date data = new Date(78797941);

        List<Postagem> postagensComOla = repository.findAllByData(data);

        Assertions.assertThrows(NoSuchElementException.class, () -> postagensComOla.stream().findAny().get());
    }
}