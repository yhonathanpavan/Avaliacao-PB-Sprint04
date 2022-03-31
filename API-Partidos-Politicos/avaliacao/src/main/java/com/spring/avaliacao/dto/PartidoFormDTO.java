package com.spring.avaliacao.dto;

import com.spring.avaliacao.constants.Ideologia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoFormDTO {
    @NotNull @NotEmpty
    private String nome;
    @NotNull @NotEmpty
    private String sigla;
    @NotNull
    private Ideologia ideologia;
    @NotEmpty
    private LocalDate dataDeFundacao;
}
