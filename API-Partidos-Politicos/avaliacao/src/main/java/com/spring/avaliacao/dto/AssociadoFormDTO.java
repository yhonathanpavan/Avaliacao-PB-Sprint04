package com.spring.avaliacao.dto;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.constants.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoFormDTO {

    private String nome;
    private CargoPolitico cargoPolitico;
    private LocalDate dataNascimento;
    private Sexo sexo;
}
