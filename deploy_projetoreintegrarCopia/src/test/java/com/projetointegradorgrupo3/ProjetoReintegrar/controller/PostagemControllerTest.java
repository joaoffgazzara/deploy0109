package com.projetointegradorgrupo3.ProjetoReintegrar.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
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
public class PostagemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void deveriaDevolverStatus200AoRequisitarGetEmPostagem() throws Exception {

        URI uri = new URI("/postagem/");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @Order(2)
    public void deveriaRetornarUmaListaComDuasPostagens() throws Exception {

        URI uri = new URI("/postagem/");

        mockMvc.perform(
                MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo", Matchers.is("Olá mundo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].corpo", Matchers.is("fala galera, como vai?")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].titulo", Matchers.is("Java")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].corpo", Matchers.is("gente, como faz para uma classe herdar da outra?")));
    }

    @Test
    @Order(3)
    public void deveriaPostarComSucesso() throws Exception {

        String postagemTeste = "{" +
                "\"titulo\":\"postagem teste\"," +
                "\"corpo\":\"Esta é uma postagem teste\"," +
                "\"usuario\":{" +
                    "\"id\":1" +
                "}," +
                "\"tema\":{" +
                    "\"id\":1" +
                "}" +
                "}";

        URI uri = new URI("/postagem/");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postagemTeste))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri + "3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo", Matchers.is("postagem teste")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.corpo", Matchers.is("Esta é uma postagem teste"))
                );
    }

    @Test
    @Order(4)
    public void deveriaAtualizarComSucesso() throws Exception{

        URI uri = new URI("/postagem/");

        String postagemTeste = "{" +
                "\"id\":3," +
                "\"titulo\":\"postagem teste atualizada\"," +
                "\"corpo\":\"Esta é uma postagem teste\"," +
                "\"usuario\":{" +
                "\"id\":1" +
                "}," +
                "\"tema\":{" +
                "\"id\":1" +
                "}" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postagemTeste))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri + "3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo", Matchers.is("postagem teste atualizada")));
    }

    @Test
    @Order(5)
    public void deveriaDeletarComSucessoEDevolver200() throws Exception {
        URI uri = new URI("/postagem/3");

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
