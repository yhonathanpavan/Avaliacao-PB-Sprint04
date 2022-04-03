package com.spring.avaliacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.avaliacao.builder.AssociadoBuilder;
import com.spring.avaliacao.builder.PartidoBuilder;
import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.constants.Sexo;
import com.spring.avaliacao.dto.VincularFormDTO;
import com.spring.avaliacao.entities.Associado;
import com.spring.avaliacao.entities.Partido;
import com.spring.avaliacao.repository.AssociadoRepository;
import com.spring.avaliacao.services.AssociadoServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.Month;
import static org.hamcrest.Matchers.*;
import java.util.Optional;

@WebMvcTest(controllers = AssociadoController.class)
class AssociadoControllerTest {

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private AssociadoRepository associadoRepository;

    @MockBean
    private AssociadoServiceImpl associadoService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @Test
    public void deveRetornarOkQuandoBuscarTodosOsAssociados() throws Exception {

        Mockito.when(this.associadoService.findAll(null, null))
                .thenReturn(AssociadoBuilder.getAllAssociadosDTO());

        mockMvc.perform(
                         get("/associados")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornarOkQuandoBuscarUmAssociadoPeloId() throws Exception {

        Mockito.when(this.associadoService.findById(1L))
                .thenReturn(AssociadoBuilder.getAssociadoDto());

        mockMvc.perform(
                         get("/associados/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is("Associado Teste")));;
    }


    @Test
    public void deveInserirUmAssociadoERetornarCreated() throws Exception {

        Associado associado = Associado.builder()
                .nome("Teste Inserido").cargoPolitico(CargoPolitico.GOVERNADOR).sexo(Sexo.FEMININO).dataNascimento(LocalDate.of(2001, Month.JANUARY, 25))
                .build();

        Mockito.when(associadoRepository.save(associado)).thenReturn(associado);

        MockHttpServletRequestBuilder mockRequest = post("/associados")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(associado));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    public void deveInserirUmPartidoERetornarOk() throws Exception {

        Partido partido = PartidoBuilder.getPartido();
        Associado associado = AssociadoBuilder.getAssociado();

        VincularFormDTO vincularForm = new VincularFormDTO(associado.getId(), partido.getId());

        associado.setPartido(partido);
        Mockito.when(associadoRepository.save(associado)).thenReturn(associado);

        MockHttpServletRequestBuilder mockRequest = post("/associados/partidos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(vincularForm));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void deveAtualizarUmAssociadoPeloIdERetornarOk() throws Exception {

        Associado atualizaAssociado = Associado.builder()
                .id(1L).nome("Teste Inserido").cargoPolitico(CargoPolitico.GOVERNADOR).sexo(Sexo.FEMININO).dataNascimento(LocalDate.of(2001, Month.JANUARY, 25))
                .build();

        Associado primeiroAssociado = AssociadoBuilder.getAssociado();

        Mockito.when(associadoRepository.findById(primeiroAssociado.getId())).thenReturn(Optional.of(primeiroAssociado));
        Mockito.when(associadoRepository.save(atualizaAssociado)).thenReturn(atualizaAssociado);

        MockHttpServletRequestBuilder mockRequest = put("/associados/{id}", primeiroAssociado.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(atualizaAssociado));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void deveDeletarAssociadoPorIdERetornarOk() throws Exception {

        Associado associado = AssociadoBuilder.getAssociado();

        Mockito.when(associadoRepository.findById(associado.getId())).thenReturn(Optional.of(associado));


        mockMvc.perform(
                delete("/associados/{id}", associado.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void deveDeletarPartidoPeloIdDoAssociadoEDoPartidoERetornarOk() throws Exception {

        Associado associado = AssociadoBuilder.getAssociado();
        Partido partido = PartidoBuilder.getPartido();

        Mockito.when(associadoService.deletePartidoById(associado.getId(), partido.getId())).thenReturn(null);

        mockMvc.perform(delete("/associados/{idAssociado}/partidos/{idPartido}", associado.getId(), partido.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}