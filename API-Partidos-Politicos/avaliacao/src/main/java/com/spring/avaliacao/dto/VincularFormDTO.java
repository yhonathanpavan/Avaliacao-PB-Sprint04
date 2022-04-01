package com.spring.avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VincularFormDTO{

    @NotNull @NotEmpty
    private Long idAssociado;
    @NotNull @NotEmpty
    private Long idPartido;

}
