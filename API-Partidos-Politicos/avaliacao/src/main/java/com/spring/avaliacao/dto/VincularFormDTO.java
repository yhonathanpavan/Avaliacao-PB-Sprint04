package com.spring.avaliacao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VincularFormDTO{

    @NotNull
    private Long idAssociado;
    @NotNull
    private Long idPartido;

}
