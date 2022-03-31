package com.spring.avaliacao.dto;

import com.spring.avaliacao.constants.Ideologia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDTO {
    private Long id;
    private String nome;
    private String sigla;
    private Ideologia ideologia;
    private Date dataDeFundacao;
}
