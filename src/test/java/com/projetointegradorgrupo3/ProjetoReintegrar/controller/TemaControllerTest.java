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
public class TemaControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void deveriaDevolverStatus200AoRequisitarGetEmTema() throws Exception {

        URI uri = new URI("/tema/");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @Order(2)
    public void deveriaRetornarUmaListaComDoisTemas() throws Exception {

        URI uri = new URI("/tema/");

        mockMvc.perform(
                MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome", Matchers.is("apresentacao")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].nome", Matchers.is("tecnologia")));
    }



    @Test
    @Order(3)
    public void deveriaPostarComSucesso() throws Exception {

        String novoTema = "{" +
                "\"nome\":\"tema teste\"" +
                "}";

        URI uri = new URI("/tema/");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoTema))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri + "3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("tema teste")));

    }

    @Test
    @Order(4)
    public void deveriaAtualizarComSucesso() throws Exception{

        String novoAtualizado = "{" +
                "\"id\":3," +
                "\"nome\":\"tema teste atualizado\"" +
                "}";

        URI uri = new URI("/tema/");


        mockMvc.perform(
                MockMvcRequestBuilders
                        .put(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoAtualizado))
                .andExpect(MockMvcResultMatchers.status().isOk());

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get(uri + "3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome", Matchers.is("tema teste atualizado")));

    }

    @Test
    @Order(5)
    public void deveriaDeletarComSucessoEDevolver200() throws Exception {
        URI uri = new URI("/tema/3");

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