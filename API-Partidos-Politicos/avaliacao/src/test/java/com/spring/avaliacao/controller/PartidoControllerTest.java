package com.spring.avaliacao.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.avaliacao.builder.PartidoBuilder;
import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.dto.AssociadoDTO;

import com.spring.avaliacao.entities.Partido;
import com.spring.avaliacao.repository.PartidoRepository;

import com.spring.avaliacao.services.PartidoServiceImpl;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.*;


@WebMvcTest(controllers = PartidoController.class)
public class PartidoControllerTest {

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private PartidoRepository partidoRepository;

    @MockBean
    private PartidoServiceImpl partidoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @Test
    public void deveRetornarOkQuandoBuscarTodosOsPartidos() throws Exception {

        Mockito.when(this.partidoService.findAll(null, null))
                .thenReturn(PartidoBuilder.getAllPartidosDTO());

        mockMvc.perform(
                         get("/partidos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarOkQuandoBuscarUmPartidoPeloId() throws Exception {

        Mockito.when(this.partidoService.findById(1L))
                .thenReturn(PartidoBuilder.getPartidoDTO());

        mockMvc.perform(
                         get("/partidos/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Movimento Democrático Brasileiro")));;
    }

    @Test
    public void deveRetornarOkQuandoBuscarUmListaDeAssociadosDeUmPartidoPeloId() throws Exception {

        Partido partido = PartidoBuilder.getPartido();

        List<AssociadoDTO>  associadoDTOS = PartidoBuilder.getAssociadosVinculados();

        Mockito.when(this.partidoService.findAllAssociados(partido.getId()))
                .thenReturn(associadoDTOS);

        mockMvc.perform(
                         get("/partidos/{id}/associados", partido.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome", is("Joao Teste")));;
    }

    @Test
    public void deveInserirUmPartidoERetornarCreated() throws Exception {

        Partido partido = Partido.builder()
                .nome("Teste Inserido").sigla("TI").ideologia(Ideologia.CENTRO).dataDeFundacao(LocalDate.of(2001, Month.JANUARY, 25))
                .build();

        Mockito.when(partidoRepository.save(partido)).thenReturn(partido);

        MockHttpServletRequestBuilder mockRequest = post("/partidos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(partido));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    public void deveAtualizarUmPartidoPeloIdERetornarOk() throws Exception {

        Partido atualizaPartido = Partido.builder()
                .id(1L).nome("Teste Atualizado").sigla("TI").ideologia(Ideologia.CENTRO).dataDeFundacao(LocalDate.of(2001, Month.JANUARY, 25))
                .build();

        Partido primeiroPartido = PartidoBuilder.getPartido();

        Mockito.when(partidoRepository.findById(primeiroPartido.getId())).thenReturn(Optional.of(primeiroPartido));
        Mockito.when(partidoRepository.save(atualizaPartido)).thenReturn(atualizaPartido);

        MockHttpServletRequestBuilder mockRequest = put("/partidos/{id}", primeiroPartido.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(atualizaPartido));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void deletarPartidoPorIdERetornarOk() throws Exception {

        Partido partido = PartidoBuilder.getPartido();

        mockMvc.perform(
                         delete("/partidos/{id}", partido.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}