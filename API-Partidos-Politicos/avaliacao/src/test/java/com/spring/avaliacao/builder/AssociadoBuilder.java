package com.spring.avaliacao.builder;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.constants.Sexo;
import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import com.spring.avaliacao.entities.Associado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssociadoBuilder {


    public static AssociadoDTO getAssociadoDto(){
        return new AssociadoDTO(1L, "Associado Teste", CargoPolitico.GOVERNADOR, LocalDate.of(1980, Month.JANUARY, 15), Sexo.FEMININO);
    }

    public static Associado getAssociado(){
        return new Associado(1L, "Associado Teste", CargoPolitico.GOVERNADOR, LocalDate.of(1980, Month.JANUARY, 15), Sexo.FEMININO, PartidoBuilder.getPartido());
    }

    public static Page<AssociadoDTO> getAllAssociadosDTO(){
        AssociadoDTO associado1 = new AssociadoDTO(1L, "Associado Teste1", CargoPolitico.GOVERNADOR, LocalDate.of(1980, Month.JANUARY, 15), Sexo.FEMININO);

        AssociadoDTO associado2 = new AssociadoDTO(1L, "Associado Teste2", CargoPolitico.GOVERNADOR, LocalDate.of(1980, Month.JANUARY, 15), Sexo.FEMININO);

        AssociadoDTO associado3 = new AssociadoDTO(1L, "Associado Test3", CargoPolitico.GOVERNADOR, LocalDate.of(1980, Month.JANUARY, 15), Sexo.FEMININO);

        List<AssociadoDTO> listaDto = new ArrayList<>(Arrays.asList(associado1, associado2, associado3));

        return new PageImpl<>(listaDto);
    }

    public static AssociadoFormDTO getAssociadoForm(){
        return new AssociadoFormDTO("Teste Form", CargoPolitico.GOVERNADOR,  LocalDate.of(1980, Month.JANUARY, 15), Sexo.FEMININO);
    }





}
