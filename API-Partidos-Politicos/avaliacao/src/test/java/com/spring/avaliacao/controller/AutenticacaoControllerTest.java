package com.spring.avaliacao.controller;

import com.spring.avaliacao.exception.ObjectNotFound.ObjectNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutenticacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveRetornarOkCasoAssociadoSejaVinculado() throws Exception {
        URI uri = new URI("/associados/partidos");
        String json = "{" +
                "\"idAssociado\":\"3\"," +
                "\"idPartido\":\"1\"" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isOk());
    }

    @Test
    public void deveRetornarBadRequestCasoAssociadoNaoSejaVinculado() throws Exception {
        URI uri = new URI("/associados/partidos");
        String json = "{" +
                "\"idAssociado\":\"\"," +
                "\"idPartido\":\"\"" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().isBadRequest());
    }

    @Test
    public void deveRetornarNotFoundtCasoNaoEncontreUmAssociado() throws Exception {
        URI uri = new URI("/associados/9999");
        String json = "{" +
                "\"timestamp\": \"" + System.currentTimeMillis() + "\"," +
                "\"status\": 404,\"" +
                "\"error\": \"Não encontrado\"," +
                "\"message\": \"Associado não encontrado!\"," +
                "\"path\": \"associados/9999\"" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(result ->  assertTrue(result.getResolvedException() instanceof ObjectNotFoundException))
                .andExpect(result ->
                        assertEquals("Associado não encontrado!", result.getResolvedException().getMessage()));
    }
}
