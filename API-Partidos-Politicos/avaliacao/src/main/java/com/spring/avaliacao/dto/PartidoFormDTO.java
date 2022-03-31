package com.spring.avaliacao.dto;

import com.spring.avaliacao.constants.Ideologia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoFormDTO {
    private String nome;
    private String sigla;
    private Ideologia ideologia;
    private LocalDate dataDeFundacao;
}
