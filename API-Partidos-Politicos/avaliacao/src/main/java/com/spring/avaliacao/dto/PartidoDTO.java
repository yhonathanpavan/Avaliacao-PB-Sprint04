package com.spring.avaliacao.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.serializer.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDTO {
    private Long id;
    private String nome;
    private String sigla;
    private Ideologia ideologia;
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dataDeFundacao;
}
