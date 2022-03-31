package com.spring.avaliacao.dto;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.constants.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoFormDTO {

    @NotNull @NotEmpty
    private String nome;
    @NotNull
    private CargoPolitico cargoPolitico;
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    private Sexo sexo;
}
