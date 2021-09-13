package com.projetointegradorgrupo3.ProjetoReintegrar.repository;

import com.projetointegradorgrupo3.ProjetoReintegrar.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    public void deveriaProcurarUsuariosPorNome(){
        List<Usuario> usuarios = repository.findAllByNomeContainingIgnoreCase("Jose");

        Assertions.assertTrue(usuarios.size() > 0);
    }

    @Test
    public void deveriaCarregarTodosOsUsuariosFemininos(){
        List<Usuario> femininas = repository.findAllByGeneroContainingIgnoreCase("feminino");

        Assertions.assertEquals("Maria", femininas.stream().findAny().get().getNome());
    }

    @Test
    public void deveriaProcurarUsuariosPorEmail(){
        List<Usuario> usuarios = repository.findAllByEmailContainingIgnoreCase("email");

        Assertions.assertTrue(usuarios.size() > 0);
    }

    @Test
    public void NaoDeveriaAcharUmUsuarioPorEmailNaoCadastrado(){
        Optional<Usuario> optionalUsuario = repository.findByEmail("emailinvalido@email.com");

        Assertions.assertThrows(NoSuchElementException.class, optionalUsuario::get);
    }
}
