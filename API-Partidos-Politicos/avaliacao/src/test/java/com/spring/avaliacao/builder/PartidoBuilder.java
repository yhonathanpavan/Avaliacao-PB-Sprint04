package com.spring.avaliacao.builder;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.constants.Sexo;
import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.PartidoDTO;
import com.spring.avaliacao.dto.PartidoFormDTO;
import com.spring.avaliacao.entities.Associado;
import com.spring.avaliacao.entities.Partido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PartidoBuilder {



    public static PartidoDTO getPartidoDTO(){
        return new PartidoDTO(1L, "Movimento Democrático Brasileiro", "MDB", Ideologia.CENTRO, LocalDate.of(1980, Month.JANUARY, 15));
    }

    public static Partido getPartido(){
        return new Partido(1L, "Movimento Democrático Brasileiro", "MDB", Ideologia.CENTRO, LocalDate.of(1980, Month.JANUARY, 15));
    }

    public static Page<PartidoDTO> getAllPartidosDTO(){
        PartidoDTO partido1 = new PartidoDTO(1L, "Movimento Democrático Brasileiro", "MDB", Ideologia.CENTRO, LocalDate.of(1980, Month.JANUARY, 15));
        PartidoDTO partido2 = new PartidoDTO(2L, "Partido dos Trabalhadores", "PT", Ideologia.ESQUERDA, LocalDate.of(1980, Month.FEBRUARY, 10));
        PartidoDTO partido3 = new PartidoDTO(3L, "Partido da Social Democracia Brasileira", "PSDB", Ideologia.CENTRO, LocalDate.of(1980, Month.JUNE, 25));

        List<PartidoDTO> listaDto = new ArrayList<>(Arrays.asList(partido1, partido2, partido3));

        return new PageImpl<>(listaDto);
    }

    public static PartidoFormDTO getPartidoForm(){
        return new PartidoFormDTO("Teste Form", "TF", Ideologia.CENTRO, LocalDate.of(1980, Month.JANUARY, 15));
    }

    public static List<AssociadoDTO> getAssociadosVinculados(){

        ModelMapper modelMapper = new ModelMapper();

        Partido partido = getPartido();

        List<Associado> associados = new ArrayList<>();
        Associado associado1 = new Associado(1L, "Joao Teste", CargoPolitico.NENHUM, LocalDate.of(2020, Month.JANUARY, 10), Sexo.MASCULINO, partido);
        Associado associado2 = new Associado(2L, "Maria Teste", CargoPolitico.NENHUM, LocalDate.of(2020, Month.JANUARY, 10), Sexo.FEMININO, partido);
        associados.add(associado1);
        associados.add(associado2);

        List<AssociadoDTO> associadoDTOS = associados.stream().map(e -> modelMapper.map(e, AssociadoDTO.class)).collect(Collectors.toList());
        return  associadoDTOS;
    }



}
