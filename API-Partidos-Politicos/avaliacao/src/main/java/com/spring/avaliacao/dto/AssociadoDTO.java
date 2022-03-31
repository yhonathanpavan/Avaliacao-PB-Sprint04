package com.spring.avaliacao.dto;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.constants.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDTO {

    private Long id;
    private String nome;
    private CargoPolitico cargoPolitico;
    private Date dataNascimento;
    private Sexo sexo;
}
