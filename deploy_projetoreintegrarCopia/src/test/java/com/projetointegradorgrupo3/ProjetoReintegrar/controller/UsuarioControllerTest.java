package com.projetointegradorgrupo3.ProjetoReintegrar.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void deveriaDevolverStatus200AoRequisitarGetEmUsuarios() throws Exception {

        URI uri = new URI("/usuarios/");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @Order(2)
    public void deveriaRetornarUmaListaComDoisUsuarios() throws Exception {

        URI uri = new URI("/usuarios/");

        mockMvc.perform(
                MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("Jose Santos")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].genero", Matchers.is("masculino")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email", Matchers.is("email@email.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].telefone", Matchers.is("(11) 99999-9999")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].pretensaoSalarial", Matchers.is(2000.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].foto", Matchers.is("http://imgur.com/imagem")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome", Matchers.is("Maria")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].genero", Matchers.is("feminino")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].email", Matchers.is("email2@email.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].telefone", Matchers.is("(11) 99999-9999")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].pretensaoSalarial", Matchers.is(2500.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].foto", Matchers.is("http://imgur.com/imagem")));
    }

    @Test
    @Order(3)
    public void deveriaPostarComSucesso() throws Exception {

        String novoUsuario = "{" +
                "\"nome\":\"Moises\"," +
                "\"genero\":\"masculino\"," +
                "\"email\":\"email3@email.com\"," +
                "\"telefone\":\"(11) 99999-9999\"," +
                "\"senha\":\"12345678\"," +
                "\"pretensaoSalarial\":1500.50," +
                "\"foto\":\"http://imgur.com/imagem\"," +
                "\"tipo\":\"ROLE_ADMINISTRADOR\"" +
                "}";

        URI uri = new URI("/usuarios/");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoUsuario))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri + "3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("Moises")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genero", Matchers.is("masculino")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", Matchers.is("email3@email.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefone", Matchers.is("(11) 99999-9999")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.senha", Matchers.is("12345678")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pretensaoSalarial", Matchers.is(1500.50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.foto", Matchers.is("http://imgur.com/imagem")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tipo", Matchers.is("ROLE_ADMINISTRADOR")));

    }

    @Test
    @Order(4)
    public void deveriaAtualizarComSucesso() throws Exception{

        URI uri = new URI("/usuarios/");

        String usuarioAtualizado = "{" +
                "\"id\":3," +
                "\"nome\":\"Moises\"," +
                "\"genero\":\"masculino\"," +
                "\"email\":\"email3@email.com\"," +
                "\"telefone\":\"(11) 92664-4584\"," +
                "\"senha\":\"12345678\"," +
                "\"pretensaoSalarial\":1500.50," +
                "\"foto\":\"http://imgur.com/imagem\"," +
                "\"tipo\":\"ROLE_ADMINISTRADOR\"" +
                "}";


        mockMvc.perform(
                MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(usuarioAtualizado))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri + "3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefone", Matchers.is("(11) 92664-4584")));
    }


    @Test
    @Order(6)
    public void deveriaDeletarComSucessoEDevolver200() throws Exception {
        URI uri = new URI("/usuarios/3");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}